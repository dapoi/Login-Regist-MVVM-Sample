package com.dapascript.assignmentscout.data.remote.model

import com.squareup.moshi.Json

data class UserResponse(

    @Json(name = "data")
    val data: List<DataItem>,

    @Json(name = "page")
    val page: Int,
)

data class DataItem(

    @Json(name = "last_name")
    val last_name: String,

    @Json(name = "avatar")
    val avatar: String,

    @Json(name = "first_name")
    val first_name: String,

    @Json(name = "email")
    val email: String
)
