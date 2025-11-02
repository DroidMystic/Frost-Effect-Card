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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                            2.dp,
                            Color.White.copy(alpha = 0.1f),
                            RoundedCornerShape(18.dp)
                        )
                )
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "VISA",
                        color = Color.White.copy(alpha = 1f),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column {
                        Text(
                            text = "•••• •••• •••• 0007",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 18.sp,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "MOHAMMAD SABIR KHAN",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp

                            )
                            Text(
                                text = "12/27",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        HorizontalDivider(
                            thickness = 20.dp,
                            color = Color.White.copy(alpha = 0.45f)
                        )
                    }
                }
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