package com.devmasterteam.tasks.service.model


import com.google.gson.annotations.SerializedName

class PersonModel {
    @SerializedName("name")
    lateinit var name: String

    @SerializedName("personKey")
    lateinit var personKey: String

    @SerializedName("token")
    lateinit var token: String
}