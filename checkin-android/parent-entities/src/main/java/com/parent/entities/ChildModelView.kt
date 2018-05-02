package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildModelView(
        var id: String = "",
        var fullName: String = "",
        var photo: String = "",
        var gender: String = "",
        var birthplace: String = "",
        var nationality: NationalityView = NationalityView(),
        var religion: String = "",
        var preferredLanguage: String = "",
        var otherLanguages: String = "",
        var classModel: ClassView = ClassView(),
        var groups: List<GroupView> = listOf(),
        var registerationDate: String = "",
        var lastDate: String = "",
        var specialNotes: String = "",
        var checkinCode: String = "",
        var keyWorker: InstituteStaffModelView = InstituteStaffModelView(),
        var emergencyContact: List<ChildContactModelView> = arrayListOf(),
        var livesWith: List<ChildContactModelView> = arrayListOf(),
        var custody: List<ChildContactModelView> = arrayListOf(),
        var personalNumber: String = "",
        var protectedAddress: Boolean = false,
        var street: String = "",
        var zipcode: String = "",
        var country: CountryView = CountryView(),
        var city: CityView = CityView(),
        var birthdate: String = "",
        var age: String = "",
        var hideStatusFromNonCustody: Boolean = false,
        var skipTags: Boolean = false,
        var lastStatus: Int = 0,
        var institutionId: String? = "",

        var currentPage: String = "0",
        var firstPageUrl: String = "",
        var from: String = "",
        var lastPage: String = "0",
        var path: String = "",
        var perPage: String = "",
        var to: String = "",
        var total: String = ""

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(NationalityView::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(ClassView::class.java.classLoader),
            parcel.createTypedArrayList(GroupView.CREATOR),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(InstituteStaffModelView::class.java.classLoader),
            parcel.createTypedArrayList(ChildContactModelView),
            parcel.createTypedArrayList(ChildContactModelView),
            parcel.createTypedArrayList(ChildContactModelView),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(CountryView::class.java.classLoader),
            parcel.readParcelable(CityView::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(fullName)
        parcel.writeString(photo)
        parcel.writeString(gender)
        parcel.writeString(birthplace)
        parcel.writeParcelable(nationality, flags)
        parcel.writeString(religion)
        parcel.writeString(preferredLanguage)
        parcel.writeString(otherLanguages)
        parcel.writeParcelable(classModel, flags)
        parcel.writeTypedList(groups)
        parcel.writeString(registerationDate)
        parcel.writeString(lastDate)
        parcel.writeString(specialNotes)
        parcel.writeString(checkinCode)
        parcel.writeParcelable(keyWorker, flags)
        parcel.writeTypedList(emergencyContact)
        parcel.writeTypedList(livesWith)
        parcel.writeTypedList(custody)
        parcel.writeString(personalNumber)
        parcel.writeByte(if (protectedAddress) 1 else 0)
        parcel.writeString(street)
        parcel.writeString(zipcode)
        parcel.writeParcelable(country, flags)
        parcel.writeParcelable(city, flags)
        parcel.writeString(birthdate)
        parcel.writeString(age)
        parcel.writeByte(if (hideStatusFromNonCustody) 1 else 0)
        parcel.writeByte(if (skipTags) 1 else 0)
        parcel.writeInt(lastStatus)
        parcel.writeString(institutionId)
        parcel.writeString(currentPage)
        parcel.writeString(firstPageUrl)
        parcel.writeString(from)
        parcel.writeString(lastPage)
        parcel.writeString(path)
        parcel.writeString(perPage)
        parcel.writeString(to)
        parcel.writeString(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildModelView> {
        override fun createFromParcel(parcel: Parcel): ChildModelView {
            return ChildModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildModelView?> {
            return arrayOfNulls(size)
        }

        const val UNKNOWN_TYPE = 0
        const val CHECK_IN_STATUS_TYPE = 1
        const val CHECK_OUT_STATUS_TYPE = 2

    }

    fun copy(from: ChildModelView) {
        this.id = from.id
        this.fullName = from.fullName
        this.photo = from.photo
        this.gender = from.gender
        this.birthplace = from.birthplace
        this.nationality = from.nationality
        this.religion = from.religion
        this.preferredLanguage = from.preferredLanguage
        this.otherLanguages = from.otherLanguages
        this.classModel = from.classModel
        this.groups = from.groups
        this.registerationDate = from.registerationDate
        this.lastPage = from.lastDate
        this.specialNotes = from.specialNotes
        this.checkinCode = from.checkinCode
        this.keyWorker = from.keyWorker
        this.classModel = from.classModel
        this.emergencyContact = from.emergencyContact
        this.livesWith = from.livesWith
        this.custody = from.custody
        this.personalNumber = from.personalNumber
        this.protectedAddress = from.protectedAddress
        this.street = from.street
        this.zipcode = from.zipcode
        this.country = from.country
        this.city = from.city
        this.birthdate = from.birthdate
        this.age = from.age
        this.hideStatusFromNonCustody = from.hideStatusFromNonCustody
        this.skipTags = from.skipTags
        this.lastStatus = from.lastStatus
        this.institutionId = from.institutionId
        this.currentPage = from.currentPage
        this.firstPageUrl = from.firstPageUrl
        this.from = from.from
        this.lastPage = from.lastPage
        this.path = from.path
        this.perPage = from.perPage
        this.to = from.to
        this.total = from.total


    }

}
