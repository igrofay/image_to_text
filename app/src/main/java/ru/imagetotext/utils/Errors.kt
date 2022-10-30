package ru.imagetotext.utils

sealed class Errors: Throwable(){
    class PhotoCreationError(message:String = "Ошибка создания фото") : Errors()
}
