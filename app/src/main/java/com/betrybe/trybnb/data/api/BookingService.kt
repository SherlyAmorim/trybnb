package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.Auth
import com.betrybe.trybnb.data.models.AuthResponse
import com.betrybe.trybnb.data.models.BookingDetails
import com.betrybe.trybnb.data.models.BookingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BookingService {
    @GET("booking")
    suspend fun getBookingResponse(): Response<List<BookingResponse>>

    @GET("booking/{id}")
    suspend fun getBookingById(
        @Header("Accept") accept: String,
        @Path("id") id: Int
    ): Response<BookingDetails>

    @POST("auth")
    suspend fun authentication(@Body auth: Auth): Response<AuthResponse>
}
