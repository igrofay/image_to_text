package ru.imagetotext.feature.main_content.model

sealed class EventMain{
    object CreatePhoto: EventMain()
    object CreateNewImage: EventMain()
    object ImageToText: EventMain()
    object ShareText: EventMain()
}
