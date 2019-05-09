package com.vicky.apps.datapoints.ui.model

import android.os.Parcel
import android.os.Parcelable

data class ResponseData(
    val _id: String,
    val about: String,
    val company: String,
    val logo: String,
    val members: List<Member>,
    val website: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Member),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(about)
        parcel.writeString(company)
        parcel.writeString(logo)
        parcel.writeTypedList(members)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseData> {
        override fun createFromParcel(parcel: Parcel): ResponseData {
            return ResponseData(parcel)
        }

        override fun newArray(size: Int): Array<ResponseData?> {
            return arrayOfNulls(size)
        }
    }
}