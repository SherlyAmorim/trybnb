package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.BookingDetails
import com.betrybe.trybnb.data.models.BookingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookingService {
    @GET("booking")
    suspend fun getBookingResponse(): Response<List<BookingResponse>>
    @GET("booking/{id}")
    suspend fun getBookingById(@Path("id") id: Int): Response<BookingDetails>
}
