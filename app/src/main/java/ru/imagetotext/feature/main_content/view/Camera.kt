package ru.imagetotext.feature.main_content.view

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.skgmn.cameraxx.CameraPreview
import ru.imagetotext.R

@Composable
fun Camera(
    imageCapture: ImageCapture,
    createImage: ()->Unit
) {
    var isBackgroundCamera by remember {
        mutableStateOf(true)
    }
    Column {
        CameraPreview(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraSelector = if(isBackgroundCamera)
                CameraSelector.DEFAULT_BACK_CAMERA
            else CameraSelector.DEFAULT_FRONT_CAMERA,
            imageCapture = imageCapture
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .shadow(8.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Divider()
            IconButton(
                onClick = { isBackgroundCamera = !isBackgroundCamera  },
                modifier = Modifier.padding(start = 18.dp).align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_flip_camera),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
            FloatingActionButton(
                onClick = createImage,
                modifier = Modifier
                    .align(Alignment.Center)
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape),
                backgroundColor = MaterialTheme.colors.background,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}