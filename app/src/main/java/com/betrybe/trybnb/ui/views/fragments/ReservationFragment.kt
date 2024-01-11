package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.BookingServiceClient
import com.betrybe.trybnb.data.models.BookingDetails
import com.betrybe.trybnb.data.models.BookingResponse
import com.betrybe.trybnb.ui.adapters.ReservationsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

private const val MAX_RESERV = 10

class ReservationFragment : Fragment() {

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
        recyclerView = view.findViewById(R.id.reservation_recycler_view)
        fetchReservations()
    }

    private fun fetchReservations() {
        var idsList: List<BookingResponse> = listOf()
        val reservationList: MutableList<BookingDetails> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()
                val responseIdsList = mBookingService.getBookingResponse()
                if (responseIdsList.isSuccessful) {
                    idsList = responseIdsList.body()!!
                    val filterIdsList = idsList.sortedBy { it.bookingId }.take(MAX_RESERV)
                    filterIdsList.forEach {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                ApiIdlingResource.increment()
                                val reservationsDetails =
                                    mBookingService.getBookingById("application/json", it.bookingId)

                                if (reservationsDetails.isSuccessful) {
                                    reservationList.add(
                                        reservationsDetails.body()!!
                                    )
                                }
                                withContext(Dispatchers.Main) {
                                    mBookingAdapter = ReservationsAdapter(reservationList)
                                    recyclerView.layoutManager = LinearLayoutManager(context)
                                    recyclerView.adapter = mBookingAdapter
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
                } else {
                    Log.d("ResponseError", "Erro ao buscar reservas")
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
