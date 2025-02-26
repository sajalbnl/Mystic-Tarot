package com.example.mystictarot.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.mystictarot.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.mystictarot.ui.theme.MysticTarotTheme
import com.example.mystictarot.utils.VideoPlayer
import com.example.mystictarot.utils.publicsansBold
import com.example.mystictarot.utils.publicsansRegular
import com.example.mystictarot.utils.publicsansSemiBold
import kotlinx.coroutines.launch


class IntroActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent(){
            MysticTarotTheme {
                IntroScreenView()
            }
        }

    }
}

@Composable
fun IntroScreenView(){
    val context = LocalContext.current

    val infiniteTransition = rememberInfiniteTransition()

    val scale = infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(videoUri = Uri.parse("android.resource://${context.packageName}/raw/mystic_background"))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "AI\n TAROT READER",
                fontSize = 32.sp,
                color = Color.White,
                fontFamily = publicsansBold,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp
            )
            Spacer(modifier = Modifier.height(14.dp))
            Image(
                painter = painterResource(id = R.drawable.tarot_card),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight(0.6f).graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value,
                        alpha = 0.6f
                    )
                    .clip(RoundedCornerShape(40.dp))
                    .border(border = BorderStroke(0.dp, Color(0xFF121212)),shape = RoundedCornerShape(40.dp))
                   ,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Discover Your Destiny",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = publicsansBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Unlock the secrets of the universe with AI-powered Tarot readings. Get insights into your past, present, and future with just a tap.",
                fontSize = 16.sp,
                fontFamily = publicsansRegular,
                color = Color.Gray,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A5ACD),
                ),
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                onClick = {

                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    fontFamily = publicsansSemiBold,
                    text ="Get Started" ,
                    color = Color("#ffffff".toColorInt()),
                    fontSize = 15.sp
                )

            }
        }
    }

}