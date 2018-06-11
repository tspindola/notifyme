package com.example.tspindola.notifyme

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize


@IgnoreExtraProperties
@Parcelize
data class Package(
    var uuid: String = "",
    var trackingNumber: String? = "",
    var recipient: String? = "",
    var location: String? = "",
    var sender: String? = "",
    var isurgent: Boolean = false,
    var isfragile: Boolean = false,
    var isheavy: Boolean = false,
    var isdelivered: Boolean = false,
    var observation: String? = ""
):Parcelable