package ru.imagetotext.feature.main_content.model

sealed class StateMain {
    object Loading: StateMain()
    object PhotographingCamera : StateMain()
    class BrowsingImage(val image: ByteArray): StateMain()
    class ReadText(val text: String): StateMain()
}