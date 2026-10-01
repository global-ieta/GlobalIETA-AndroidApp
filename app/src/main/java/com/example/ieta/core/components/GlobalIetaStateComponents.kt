package com.example.ieta.core.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun GlobalIetaLoading(
    modifier: Modifier = Modifier,
    message: String = "SYNCHRONIZING CORE DATA...",
) {
    val infiniteTransition = rememberInfiniteTransition(label = "OrbitalSweep")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "SweepRotation"
    )

    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseAlpha"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val cx = size.width / 2f
                val cy = size.height / 2f
                val radius = (size.width / 2f) - 6.dp.toPx()

                // Static outer background orbital ring
                drawCircle(
                    color = GlobalIETAColor.MutedBorder,
                    radius = radius,
                    style = Stroke(width = 2.dp.toPx())
                )

                // Rotating radar sweep arc
                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color.Transparent,
                            GlobalIETAColor.PrimaryCyan.copy(alpha = 0.15f),
                            GlobalIETAColor.PrimaryCyan
                        ),
                        center = Offset(cx, cy)
                    ),
                    startAngle = rotationAngle,
                    sweepAngle = 130f,
                    useCenter = false,
                    style = Stroke(width = 3.5.dp.toPx())
                )

                // Orbital accent node dot along sweeping head
                val nodeAngleRad = Math.toRadians((rotationAngle + 130).toDouble())
                val nodeX = cx + (radius * cos(nodeAngleRad)).toFloat()
                val nodeY = cy + (radius * sin(nodeAngleRad)).toFloat()

                drawCircle(
                    color = GlobalIETAColor.PrimaryCyan,
                    radius = 4.dp.toPx(),
                    center = Offset(nodeX, nodeY)
                )

                // Inner glowing core dot
                drawCircle(
                    color = GlobalIETAColor.AuraBlue.copy(alpha = pulseAlpha),
                    radius = 8.dp.toPx(),
                    center = Offset(cx, cy)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = message,
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )
    }
}

@Composable
fun GlobalIetaEmptyState(
    modifier: Modifier = Modifier,
    title: String = "NO DATA RECORDS FOUND",
    subtitle: String = "There are currently no items or active feeds matching your system parameters.",
    icon: ImageVector = Icons.Rounded.Inbox
) {
    GlobalIetaCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        technicalLabel = "[ SYSTEM STATUS // NO ACTIVE DATA ]",
        accentColor = GlobalIETAColor.SecondaryText
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GlobalIETAColor.MutedText,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    color = GlobalIETAColor.PrimaryText
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                style = GlobalIetaTheme.typography.body.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }
    }
}

@Composable
fun GlobalIetaErrorState(
    modifier: Modifier = Modifier,
    title: String = "SYSTEM PROTOCOL ERROR",
    errorMessage: String = "Failed to establish secure connection with Global IETA core servers.",
    onRetry: (() -> Unit)? = null
) {
    GlobalIetaCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        technicalLabel = "SYSTEM STATUS // ERROR",
        accentColor = GlobalIETAColor.Error
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.ErrorOutline,
                contentDescription = null,
                tint = GlobalIETAColor.Error,
                modifier = Modifier.size(44.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    color = GlobalIETAColor.Error
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = errorMessage,
                style = GlobalIetaTheme.typography.body.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )

            if (onRetry != null) {
                Spacer(modifier = Modifier.height(16.dp))
                GlobalIetaButton(
                    text = "RETRY SYNCHRONIZATION",
                    onClick = onRetry,
                    glowColor = GlobalIETAColor.Error,
                    accentColor = GlobalIETAColor.Error
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaStateComponentsPreview() {
    GlobalIetaTheme {
        Column {
            GlobalIetaLoading()
            GlobalIetaEmptyState()
            GlobalIetaErrorState { }
        }
    }
}
