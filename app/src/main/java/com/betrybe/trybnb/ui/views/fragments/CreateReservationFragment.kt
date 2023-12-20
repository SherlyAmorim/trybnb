package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment() {

    private lateinit var buttonCreateReservation: MaterialButton
    private lateinit var guestName: TextInputLayout
    private lateinit var guestLastName: TextInputLayout
    private lateinit var checkin: TextInputLayout
    private lateinit var checkout: TextInputLayout
    private lateinit var additionalNeeds: TextInputLayout
    private lateinit var totalPrice: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_create_reservation,
            container,
            false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCreateReservation = view.findViewById(R.id.create_reservation_button)
        guestName = view.findViewById(R.id.first_name_create_reservation)
        guestLastName = view.findViewById(R.id.last_name_create_reservation)
        checkin = view.findViewById(R.id.checkin_create_reservation)
        checkout = view.findViewById(R.id.checkout_create_reservation)
        additionalNeeds = view.findViewById(R.id.additional_needs_create_reservation)
        totalPrice = view.findViewById(R.id.total_price_create_reservation)

        buttonCreateReservation.setOnClickListener {
            if (guestName.editText?.text.isNullOrEmpty()) {
                guestName.error = "O campo Nome é obrigatório"
            }
            if (guestLastName.editText?.text.isNullOrEmpty()) {
                guestLastName.error = "O campo Sobrenome é obrigatório"
            }
            if (checkin.editText?.text.isNullOrEmpty()) {
                checkin.error = "O campo Checkin é obrigatório"
            }
            if (checkout.editText?.text.isNullOrEmpty()) {
                checkout.error = "O campo Checkout é obrigatório"
            }
            if (additionalNeeds.editText?.text.isNullOrEmpty()) {
                additionalNeeds.error = "O campo Necessidades Adicionais é obrigatório"
            }
            if (totalPrice.editText?.text.isNullOrEmpty()) {
                totalPrice.error = "O campo Preço Total é obrigatório"
            }
        }
    }
}
