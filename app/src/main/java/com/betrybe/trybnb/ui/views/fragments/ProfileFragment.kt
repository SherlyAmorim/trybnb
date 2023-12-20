package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

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
            if (textLogin.editText?.text.isNullOrEmpty()) {
                textLogin.error = "O campo Login é obrigatório"
            }
            if (textPassword.editText?.text.isNullOrEmpty()) {
                textPassword.error = "O campo Password é obrigatório"
            }
        }
    }
}
