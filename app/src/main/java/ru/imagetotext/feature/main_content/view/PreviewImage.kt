package ru.imagetotext.feature.main_content.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import ru.imagetotext.R
import ru.imagetotext.feature.main_content.model.StateMain

@Composable
fun PreviewImage(
    image: ByteArray,
    onBack:()->Unit,
    readImage: ()->Unit
) {
    Column {
        GlideImage(
            imageModel = image,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
                onClick = onBack,
                modifier = Modifier.padding(start = 18.dp).align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
            Button(
                onClick = readImage,
                modifier = Modifier
                    .align(Alignment.Center)
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background,
                ),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_eye),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Прочитать",
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp
                )
            }
        }
    }
}