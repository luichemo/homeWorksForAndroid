package com.example.davaleba_2

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val email : String ?= null,
    val uid : String ?= null,
    val link : String ?= null,
    val username: String ?= null)
