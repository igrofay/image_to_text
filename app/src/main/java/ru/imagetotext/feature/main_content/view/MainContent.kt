package ru.imagetotext.feature.main_content.view

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ru.imagetotext.feature.main_content.model.EventMain
import ru.imagetotext.feature.main_content.model.StateMain
import ru.imagetotext.feature.main_content.view_model.MainViewModel

@Composable
fun MainContent(
    mainVM: MainViewModel = hiltViewModel()
) {
    val state by remember { mainVM.stateMain }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(mainVM.message){
        mainVM.message.value?.message?.let { message->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Transparent
    ){
        when(state){
            StateMain.PhotographingCamera -> Camera(
                imageCapture = mainVM.imageCapture
            ){
                mainVM.onEvent(EventMain.CreatePhoto)
            }
            StateMain.Loading -> Loading()
            is StateMain.BrowsingImage -> PreviewImage(
                image = (state as StateMain.BrowsingImage).image,
                onBack = {mainVM.onEvent(EventMain.CreateNewImage)},
                readImage = {mainVM.onEvent(EventMain.ImageToText)}
            )
            is StateMain.ReadText -> ReaderText(
                (state as StateMain.ReadText).text,
                onBack = {mainVM.onEvent(EventMain.CreateNewImage)},
                share = {mainVM.onEvent(EventMain.ShareText)}
            )
        }
    }
}