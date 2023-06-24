package com.akmal.bandungcompose.model

import android.os.Parcel
import android.os.Parcelable

data class TempatWisata(
    var id: String,
    var img: String,
    var name: String,
    var open: String,
    var address: String,
    var description: String
)
