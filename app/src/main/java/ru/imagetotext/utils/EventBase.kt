package ru.imagetotext.utils

interface EventBase<T> {
    fun onEvent(event:T)
}