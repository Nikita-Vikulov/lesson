package com.example.lesson.remote

import javax.inject.Inject

interface IApiHolder {
    val apiService: GithubUsersService
}

class ApiHolder @Inject constructor(
   override val apiService: GithubUsersService
) : IApiHolder

