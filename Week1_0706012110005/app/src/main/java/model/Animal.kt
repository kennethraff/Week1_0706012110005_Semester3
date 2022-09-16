package model

import android.os.Parcel
import android.os.Parcelable

data class Animal

    (var nama:String?,
     var jenis: String?,
     var usia:String?,
     ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(jenis)
        parcel.writeString(usia.toString())
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }


     }