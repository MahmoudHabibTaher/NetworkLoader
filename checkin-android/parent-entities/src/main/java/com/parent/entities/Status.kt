package com.parent.entities

/**
 * Created by ahmedmahmoud on 2/19/18.
 */
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Status(val type: String,
                  val name: String,
                  val total: Int):Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    object Type {
        const val CHECK_IN = "check_in"
        const val CHECK_OUT = "check_out"
        const val CHILD_VACATION = "child_vacation"
        const val CHILD_SICK = "child_sick"
        const val ON_BUS = "on_bus"
        const val OFF_BUS = "off_bus"
        const val ON_TRIP = "on_trip"
        const val OFF_TRIP = "off_trip"
        const val DIAPER = "diaper_change"
        const val TOILET = "toilet_visit"
        const val MEAL = "meal"
        const val SLEEP = "child_sleep"
        const val WAKE_UP = "child_wake_up"
        const val ON_MEETING = "on_meeting"
        const val OFF_MEETING = "off_meeting"
        const val BIRTHDAY = "birthday"
        const val HAS_CHILD_SICK = "has_child_sick"
        const val OUCH = "ouch"
        const val MOOD = "mood"
    }

    class Builder : IBuilder<Status> {
        private var type = ""
        private var name = ""
        private var total = 0

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun total(total: Int): Builder {
            this.total = total
            return this
        }


        override fun build(): Status =
                Status(type, name, total)
    }




    class TestBuilder {
        companion object {
            fun buildValidStatus() =
                    Builder().type("check_in")
                            .name("Checked In")
                            .total(10)
                            .build()

            fun buildEmptyStatus() =
                    Builder().type("")
                            .name("")
                            .total(0)
                            .build()
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeInt(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Status> {
        override fun createFromParcel(parcel: Parcel): Status {
            return Status(parcel)
        }

        override fun newArray(size: Int): Array<Status?> {
            return arrayOfNulls(size)
        }
    }
}