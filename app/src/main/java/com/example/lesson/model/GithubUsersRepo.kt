package com.example.lesson.model

class GithubUsersRepo {

    fun getUsers() = ApiHolder.apiService.getUsers("/users")

//    private val users = listOf(
//        GithubUser("user1"),
//        GithubUser("user2"),
//        GithubUser("user3"),
//        GithubUser("user4"),
//        GithubUser("user5")
//    )
//
//    fun getUsers() = users.toObservable()
}





