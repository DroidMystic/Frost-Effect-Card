package com.sabir.glassmorphism

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sabir.glassmorphism.ui.theme.GlassmorphismTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlassmorphismTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    DebitCard()
                }
            }
        }
    }
}

@Composable
fun DebitCard() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
            //Card
            Box(
                modifier = Modifier
                    .size(340.dp, 200.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Transparent)
            ) {
                //Duplicating Image because Android doesn't support backdrop blur   directly
                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            //Blur Effect only supports Android version >=31
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                renderEffect = RenderEffect
                                    .createBlurEffect(
                                        20f,
                                        20f,
                                        Shader.TileMode.DECAL
                                    )
                                    .asComposeRenderEffect()
                            }
                        },
                    contentScale = ContentScale.None
                )
                //Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.14f))
                        .border(
                            1.dp,
                            Color.White.copy(alpha = 0.1f),
                            RoundedCornerShape(16.dp)
                        )
                )

            }
        }
    }

@Preview(showBackground = true)
@Composable
fun DebitCardPreview() {
    GlassmorphismTheme {
        DebitCard()
    }
}