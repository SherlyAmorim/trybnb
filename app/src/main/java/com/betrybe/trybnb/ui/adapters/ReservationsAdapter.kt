package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.BookingDetails

class ReservationsAdapter(private val reservationsList: List<BookingDetails>) :
    RecyclerView.Adapter<ReservationsAdapter.ReservationsViewHolder>() {
    class ReservationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.name_item_reservation)
        val lastName: TextView = view.findViewById(R.id.name_item_reservation)
        val totalPrice: TextView = view.findViewById(R.id.total_price_item_reservation)
        val bookingCheckin: TextView = view.findViewById(R.id.checkin_item_reservation)
        val bookingCheckout: TextView = view.findViewById(R.id.checkout_item_reservation)
        val additionalNeeds: TextView = view.findViewById(R.id.additional_needs_item_reservation)
    }

    override fun getItemCount(): Int = reservationsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservation, parent, false)
        return ReservationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationsViewHolder, position: Int) {
        holder.firstName.text = reservationsList[position].firstName
        holder.lastName.text = reservationsList[position].lastName
        holder.totalPrice.text = reservationsList[position].totalPrice.toString()
        holder.bookingCheckin.text = reservationsList[position].bookingDates.checkInt
        holder.bookingCheckout.text = reservationsList[position].bookingDates.checkOut
        holder.additionalNeeds.text = reservationsList[position].additionalNeeds
    }
}
