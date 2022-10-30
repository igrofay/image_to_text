package ru.imagetotext.data.repos

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import ru.imagetotext.domain.repos.FeaturesRepository

class FeaturesRepositoryImpl(
    private val client: HttpClient
): FeaturesRepository {
    override suspend fun imageToText(image: ByteArray): List<String> {
        return client.submitFormWithBinaryData(
            url = "/img_to_text",
            formData = formData {
                append("description", "")
                append("image", image, Headers.build {
                    append(HttpHeaders.ContentType, "image/png")
                    append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                })
            }
        ).body()
    }
}