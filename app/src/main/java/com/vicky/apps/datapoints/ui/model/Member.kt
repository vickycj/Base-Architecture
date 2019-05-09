package com.vicky.apps.datapoints.ui.model

import android.os.Parcel
import android.os.Parcelable

data class Member(
    val _id: String,
    val age: Int,
    val email: String,
    val name: Name,
    val phone: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Name::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeInt(age)
        parcel.writeString(email)
        parcel.writeParcelable(name, flags)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Member> {
        override fun createFromParcel(parcel: Parcel): Member {
            return Member(parcel)
        }

        override fun newArray(size: Int): Array<Member?> {
            return arrayOfNulls(size)
        }
    }
}