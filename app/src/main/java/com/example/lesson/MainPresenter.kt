package com.example.lesson

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel
) {

    fun counterClick(index: Int) {
        val nextCount = model.next(index)
        view.setButtonText(index, nextCount.toString())
    }
}