package com.dapascript.assignmentscout.data.remote.network

import com.dapascript.assignmentscout.data.remote.model.LoginRequest
import com.dapascript.assignmentscout.data.remote.model.LoginResponse
import com.dapascript.assignmentscout.data.remote.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Copyright (c) 2022 DapaScript. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */
interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 2,
    ): UserResponse

    // Login
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}