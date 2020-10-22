package com.example.famin

import android.os.Parcel
import android.os.Parcelable

data class CartsListModel(
    var image: String,
    var title: String,
    var price: String,
    var quantity: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartsListModel> {
        override fun createFromParcel(parcel: Parcel): CartsListModel {
            return CartsListModel(parcel)
        }

        override fun newArray(size: Int): Array<CartsListModel?> {
            return arrayOfNulls(size)
        }
    }
}