package com.parent.entities

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
class AddressModel(
        var street: String = "",
        var zipCode: String = "",
        var country: Country = Country(),
        var city: City = City()
        )