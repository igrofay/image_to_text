package ru.imagetotext.feature.main_content.view_model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skgmn.cameraxx.takePicture
import com.github.skgmn.cameraxx.toByteArray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import ru.imagetotext.domain.repos.FeaturesRepository
import ru.imagetotext.feature.main_content.model.EventMain
import ru.imagetotext.feature.main_content.model.StateMain
import ru.imagetotext.utils.Errors
import ru.imagetotext.utils.EventBase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val app: Application,
    private val repository: FeaturesRepository
):ViewModel(),EventBase<EventMain>{
    private val _state = mutableStateOf<StateMain>(StateMain.PhotographingCamera)
    private var job: Job? = null
    val stateMain: State<StateMain> get() = _state
    val imageCapture = ImageCapture.Builder()
        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
        .build()

    private var _message = mutableStateOf<Errors?>(null)
    val message:State<Errors?> get() = _message

    @SuppressLint("UnsafeOptInUsageError")
    override fun onEvent(event: EventMain) {
        when(event){
            EventMain.CreatePhoto -> {
                job?.cancel()
                job = viewModelScope.launch {
                    runCatching {
                        imageCapture.takePicture()
                    }.onSuccess { proxy->
                        val image = proxy.toByteArray()
                        proxy.close()
                        if (image == null){
                            throw Errors.PhotoCreationError()
                        }else{
                            _state.value = StateMain.BrowsingImage(image)
                        }
                    }.onFailure(::onError)
                }
            }
            EventMain.CreateNewImage -> _state.value = StateMain.PhotographingCamera
            EventMain.ImageToText -> {
                val stateLast = _state.value as StateMain.BrowsingImage
                _state.value = StateMain.Loading
                job?.cancel()
                job = viewModelScope.launch {
                    runCatching {
                        repository.imageToText(stateLast.image)
                    }.onSuccess { text->
                        _state.value = StateMain.ReadText(text.joinToString("\n"))
                    }.onFailure(::onError)
                }
            }
            EventMain.ShareText -> {
                val text = (_state.value as StateMain.ReadText).text
                val intent= Intent().apply {
                    action= Intent.ACTION_SEND
                    type="text/plain"
                    putExtra(Intent.EXTRA_TEXT,text)
                }
                val shareIntent = Intent.createChooser(intent, null).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                app.startActivity(shareIntent)
            }
        }
    }

    private fun onError(error:Throwable){
        when(error){
            is Errors.PhotoCreationError-> _message.value = error
            else -> Log.e("MainViewModel", error.message.toString())
        }
    }


}