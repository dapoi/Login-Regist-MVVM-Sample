package com.dapascript.assignmentscout.data

import com.dapascript.assignmentscout.data.remote.model.DataItem
import com.dapascript.assignmentscout.data.remote.model.LoginRequest
import com.dapascript.assignmentscout.data.remote.model.LoginResponse
import com.dapascript.assignmentscout.data.remote.network.ApiService
import com.dapascript.assignmentscout.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

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
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override fun getUser(): Flow<Resource<List<DataItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getUsers().data
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.login(loginRequest)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}