package com.astontraineeship.fragmentstraining.task2

object CreateNewId {
    private var value = 1
    fun getNext() = value++

    fun clearId() {
        value = 1
    }
}