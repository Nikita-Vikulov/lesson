package com.example.lesson.model

class GithubUsersRepo {

    fun getUsers() = ApiHolder.apiService.getUsers("/users")

}





