package com.example.lesson.view.ui

interface IImageLoader<T> {
    fun loadTo(url: String, target: T)
}