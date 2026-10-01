package com.example.ieta.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaLogoIcon
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scaleAnim = remember { Animatable(0.7f) }
    val alphaAnim = remember { Animatable(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "splashPulse")
    val rotationDegrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowPulse"
    )

    LaunchedEffect(Unit) {
        scaleAnim.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
        alphaAnim.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 600)
        )
        delay(1200)
        onSplashFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg),
        contentAlignment = Alignment.Center
    ) {
        // Radial cyan glow background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerOffset = Offset(size.width / 2f, size.height / 2f)
            val maxRadius = size.width.coerceAtLeast(size.height) * 0.75f

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        GlobalIETAColor.PrimaryCyan.copy(alpha = 0.25f * glowAlpha),
                        GlobalIETAColor.ElectricBlue.copy(alpha = 0.12f * glowAlpha),
                        GlobalIETAColor.PrimaryBg
                    ),
                    center = centerOffset,
                    radius = maxRadius
                ),
                center = centerOffset,
                radius = maxRadius
            )
        }

        // Animated Central Tech Ring
        Canvas(
            modifier = Modifier
                .size(240.dp)
                .scale(scaleAnim.value)
                .alpha(alphaAnim.value)
        ) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val r = size.width / 2f - 10.dp.toPx()

            rotate(degrees = rotationDegrees, pivot = center) {
                // Dashed outer orbit ring
                drawCircle(
                    color = GlobalIETAColor.PrimaryCyan.copy(alpha = 0.4f),
                    radius = r,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(15f, 25f), 0f
                        )
                    )
                )

                // Counter-orbit ticks
                drawCircle(
                    color = GlobalIETAColor.ElectricBlue.copy(alpha = 0.3f),
                    radius = r * 0.85f,
                    style = Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(40f, 15f), 0f
                        )
                    )
                )
            }
        }

        // Center Content Box
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scale(scaleAnim.value)
                .alpha(alphaAnim.value)
                .padding(24.dp)
        ) {
            GlobalIetaLogoIcon(size = 72.dp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "GLOBAL IETA",
                style = GlobalIetaTheme.typography.display.copy(
                    fontSize = 32.sp,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CONNECTED WORLDS · ONE FOUNDATION",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 11.sp,
                    letterSpacing = 2.5.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "[ INITIALIZING MESH ARCHITECTURE // 100% ]",
                style = GlobalIetaTheme.typography.small.copy(
                    fontFamily = FontFamily.Monospace,
                    color = GlobalIETAColor.MutedText,
                    letterSpacing = 1.5.sp
                )
            )
        }
    }
}
