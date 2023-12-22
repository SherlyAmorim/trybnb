package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
// import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// import androidx.lifecycle.lifecycleScope
// import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
// import com.betrybe.trybnb.data.api.BookingServiceClient
// import com.betrybe.trybnb.ui.adapters.ReservationsAdapter
// import kotlinx.coroutines.launch

class ReservationFragment : Fragment() {

//    private val mBookingService = BookingServiceClient.instance
//    private lateinit var mBookingAdapter: ReservationsAdapter
//    private lateinit var recyclerView: RecyclerView

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
//        recyclerView = view.findViewById(R.id.reservation_recycler_view)
//
//        fetchReservations()
//
//        return view
    }

//    private fun fetchReservations() {
//        lifecycleScope.launch {
//            val response = mBookingService.getBookingResponse()
//            if (response.isSuccessful) {
//                val reservations = response.body()
//                Log.d("Reservation", reservations.toString())
//
//                mBookingAdapter = ReservationsAdapter(reservations)
//                recyclerView.adapter = mBookingAdapter
//            } else {
//                "Erro"
//            }
//        }
//    }
}
