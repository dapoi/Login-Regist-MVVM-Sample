package com.dapascript.assignmentscout.data

import com.dapascript.assignmentscout.data.remote.model.DataItem
import com.dapascript.assignmentscout.data.remote.model.LoginRequest
import com.dapascript.assignmentscout.data.remote.model.LoginResponse
import com.dapascript.assignmentscout.util.Resource
import kotlinx.coroutines.flow.Flow

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
interface UserRepository {
    fun getUser(): Flow<Resource<List<DataItem>>>

    fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
}