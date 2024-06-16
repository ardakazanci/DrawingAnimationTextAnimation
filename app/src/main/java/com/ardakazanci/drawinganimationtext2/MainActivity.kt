package com.ardakazanci.drawinganimationtext2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.ardakazanci.drawinganimationtext2.ui.theme.DrawingAnimationText2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawingAnimationText2Theme {
                Box(modifier = Modifier.fillMaxSize().background(Color(0XFF282c34)), contentAlignment = Alignment.Center) {
                    AnimatedText()
                }
            }
        }
    }
}

@Composable
fun AnimatedText() {
    val messages = listOf("Hello World!", "¡Hola Mundo!", "Hallo Welt!", "Ciao Mondo!","हालो!")
    val messageColors = listOf(Color(0xFFfa8231), Color(0xFFc678dd), Color(0xFFa9b0bd), Color.White,Color(0xFFfa8231))
    var currentMessageIndex by remember { mutableIntStateOf(0) }
    val currentMessage = messages[currentMessageIndex]
    val currentColor = messageColors[currentMessageIndex]

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentMessageIndex = (currentMessageIndex + 1) % messages.size
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = "println(",
                color = Color(0xFF61afef),
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                fontSize = 24.sp
            )
            Text(
                text = "\"",
                color = Color(0xFFE3BB68),
                fontSize = 24.sp
            )
            AnimatedContent(
                targetState = currentMessage,
                transitionSpec = {
                    slideInVertically { height -> height } + fadeIn() togetherWith
                            slideOutVertically { height -> -height } + fadeOut()
                }, label = "currentMessageLabel"
            ) { targetMessage ->
                Text(
                    text = targetMessage,
                    fontSize = 24.sp,
                    color = currentColor
                )
            }
            Text(
                text = "\"",
                color = Color(0xFFE3BB68),
                fontSize = 24.sp
            )
            Text(
                text = ")",
                color = Color(0xFF61afef),
                fontSize = 24.sp
            )
        }
    }
}

