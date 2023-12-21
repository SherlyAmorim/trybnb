package com.betrybe.trybnb.data.models

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    @SerializedName("bookingid")
    val bookingId: Int
)
