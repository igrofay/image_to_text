package ru.imagetotext.domain.repos

interface FeaturesRepository{
    suspend fun imageToText(image:ByteArray): List<String>
}