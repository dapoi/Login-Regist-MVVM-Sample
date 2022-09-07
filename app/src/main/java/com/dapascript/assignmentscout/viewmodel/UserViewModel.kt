package com.dapascript.assignmentscout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dapascript.assignmentscout.data.UserRepositoryImpl
import com.dapascript.assignmentscout.data.remote.model.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepositoryImpl) :
    ViewModel() {
    fun getUser() = userRepository.getUser().asLiveData()

    fun login(username: String, password: String) = userRepository.login(
        LoginRequest(
            username,
            password
        )
    ).asLiveData()
}