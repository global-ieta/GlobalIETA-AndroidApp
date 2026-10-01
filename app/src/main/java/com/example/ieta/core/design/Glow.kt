package com.example.ieta.core.design

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object GlobalIetaGlow {
    val DefaultGlowColor: Color = GlobalIETAColor.PrimaryCyan
    val DefaultGlowRadius: Dp = 12.dp
}

/**
 * Renders a soft, localized, lightweight radial glow behind rectangular / rounded-rect components
 * using GPU-accelerated native radial gradient brushes without software blur stacks or performance penalties.
 */
fun Modifier.cyanGlow(
    color: Color = GlobalIETAColor.PrimaryCyan,
    blurRadius: Dp = 12.dp,
    spread: Dp = 2.dp,
    alpha: Float = 0.4f,
    cornerRadius: Dp = 8.dp
): Modifier = this.drawBehind {
    val spreadPx = spread.toPx()
    val blurPx = blurRadius.toPx()
    val totalGlowExtent = spreadPx + blurPx
    val centerOffset = Offset(size.width / 2f, size.height / 2f)
    val maxDimension = maxOf(size.width, size.height)
    val gradientRadius = (maxDimension / 2f + totalGlowExtent).coerceAtLeast(1f)

    drawRoundRect(
        brush = Brush.radialGradient(
            colorStops = arrayOf(
                0.0f to color.copy(alpha = alpha),
                0.45f to color.copy(alpha = alpha * 0.55f),
                0.75f to color.copy(alpha = alpha * 0.18f),
                1.0f to Color.Transparent
            ),
            center = centerOffset,
            radius = gradientRadius
        ),
        topLeft = Offset(-totalGlowExtent, -totalGlowExtent),
        size = Size(
            size.width + totalGlowExtent * 2f,
            size.height + totalGlowExtent * 2f
        ),
        cornerRadius = CornerRadius(cornerRadius.toPx() + totalGlowExtent / 2f)
    )
}

/**
 * Renders a soft, lightweight radial glow circle for status indicators, active radar nodes,
 * and live AI aura elements without GPU performance penalty or heavy blur stacks.
 */
fun Modifier.auraPulseGlow(
    color: Color = GlobalIETAColor.AuraBlue,
    radius: Dp = 16.dp,
    alpha: Float = 0.5f
): Modifier = this.drawBehind {
    val radiusPx = radius.toPx()
    val centerOffset = Offset(size.width / 2f, size.height / 2f)
    val maxDim = maxOf(size.width, size.height)
    val glowRadius = (maxDim / 2f + radiusPx).coerceAtLeast(1f)

    drawCircle(
        brush = Brush.radialGradient(
            colorStops = arrayOf(
                0.0f to color.copy(alpha = alpha),
                0.50f to color.copy(alpha = alpha * 0.50f),
                0.80f to color.copy(alpha = alpha * 0.15f),
                1.0f to Color.Transparent
            ),
            center = centerOffset,
            radius = glowRadius
        ),
        center = centerOffset,
        radius = glowRadius
    )
}
