package com.example.lesson.images

interface IImageLoader<T> {
    fun loadTo(url: String, target: T)
}