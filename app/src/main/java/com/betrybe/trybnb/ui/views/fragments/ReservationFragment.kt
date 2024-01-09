package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.api.BookingServiceClient
import com.betrybe.trybnb.ui.adapters.ReservationsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationFragment : Fragment() {

    private lateinit var buttonReservation: BottomNavigationView
    private val mBookingService = BookingServiceClient.instance
    private lateinit var mBookingAdapter: ReservationsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_reservation,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonReservation = view.findViewById(R.id.reservation_menu_item)

        buttonReservation.setOnClickListener {
            recyclerView = view.findViewById(R.id.reservation_frame_layout)
            fetchReservations()
        }
    }

    private fun fetchReservations() {
        lifecycleScope.launch {
            val response = mBookingService.getBookingResponse()
            if (response.isSuccessful) {
                val reservations = response.body()
                val responseId = reservations!!.mapNotNull {
                    mBookingService.getBookingById(it.bookingId).body()
                }
                withContext(Dispatchers.Main) {
                    mBookingAdapter = ReservationsAdapter(responseId)
                    recyclerView.adapter = mBookingAdapter
                }
            } else {
                Log.d("ResponseError", "Erro ao buscar reservas")
            }
        }
    }
}
