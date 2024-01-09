package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookingServiceClient
import com.betrybe.trybnb.data.models.Auth
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ProfileFragment : Fragment() {

    private lateinit var buttonLogin: Button
    private lateinit var textLogin: TextInputLayout
    private lateinit var textPassword: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_profile,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.login_button_profile)
        textLogin = view.findViewById(R.id.login_input_profile)
        textPassword = view.findViewById(R.id.password_input_profile)

        buttonLogin.setOnClickListener {
            val login = textLogin.editText?.text.toString()
            val password = textPassword.editText?.text.toString()

            validLoginFields(login, password)
            loginSuccessful(login, password)
        }
    }

    private fun validLoginFields(login: String, password: String) {
        if (login.isEmpty()) {
            textLogin.error = "O campo Login é obrigatório"
        }
        if (password.isEmpty()) {
            textPassword.error = "O campo Password é obrigatório"
        }
    }

    private fun loginSuccessful(login: String, password: String) {
        val auth = Auth(login, password)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                ApiIdlingResource.increment()
                val response = withContext(Dispatchers.IO) {
                    BookingServiceClient.instance.authentication(auth)
                }
                if (response.isSuccessful && response.body() != null) {
                    view?.findViewById<TextView>(R.id.login_successful)?.apply {
                        text = "Login feito com sucesso!"
                        visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(context, "Falha no login", Toast.LENGTH_LONG).show()
                }
            } catch (ex: HttpException) {
                withContext(Dispatchers.Main) {
                    ex.message()
                }
            } catch (ex: IOException) {
                withContext(Dispatchers.Main) {
                    ex.message
                }
            } finally {
                ApiIdlingResource.decrement()
            }
        }
    }
}
