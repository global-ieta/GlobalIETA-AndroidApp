package com.example.ieta.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.cyanGlow

@Composable
fun GlobalIetaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    glowColor: Color = GlobalIETAColor.PrimaryCyan,
    accentColor: Color = GlobalIETAColor.PrimaryCyan
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && !isLoading) 0.985f else 1.0f,
        animationSpec = tween(durationMillis = 120, easing = FastOutSlowInEasing),
        label = "ButtonPressScale"
    )

    val shape = RoundedCornerShape(12.dp)
    val backgroundBrush = if (enabled) {
        Brush.horizontalGradient(
            colors = listOf(
                accentColor,
                GlobalIETAColor.ElectricBlue
            )
        )
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                GlobalIETAColor.MutedBorder,
                GlobalIETAColor.Border
            )
        )
    }

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(
                if (enabled && !isLoading) {
                    Modifier.cyanGlow(color = glowColor, blurRadius = 10.dp, alpha = 0.35f, cornerRadius = 12.dp)
                } else Modifier
            )
            .clip(shape)
            .background(backgroundBrush)
            .border(1.dp, if (enabled) accentColor.copy(alpha = 0.8f) else GlobalIETAColor.MutedBorder, shape)
            .clickable(
                enabled = enabled && !isLoading,
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .defaultMinSize(minHeight = 52.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = GlobalIETAColor.PrimaryBg,
                strokeWidth = 2.dp
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = GlobalIETAColor.PrimaryBg,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        color = GlobalIETAColor.PrimaryBg
                    )
                )
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = GlobalIETAColor.PrimaryBg,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GlobalIetaOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    borderColor: Color = GlobalIETAColor.Border
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled && !isLoading) 0.985f else 1.0f,
        animationSpec = tween(durationMillis = 120, easing = FastOutSlowInEasing),
        label = "OutlinedButtonPressScale"
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = when {
            !enabled -> GlobalIETAColor.MutedBorder
            isPressed -> GlobalIETAColor.PrimaryCyan
            else -> borderColor
        },
        animationSpec = tween(durationMillis = 120, easing = FastOutSlowInEasing),
        label = "OutlinedButtonBorderColor"
    )

    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .background(GlobalIETAColor.DeepSurface)
            .border(
                1.dp,
                animatedBorderColor,
                shape
            )
            .clickable(
                enabled = enabled && !isLoading,
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .defaultMinSize(minHeight = 52.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = animatedBorderColor,
                strokeWidth = 2.dp
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = if (enabled) animatedBorderColor else GlobalIETAColor.MutedText,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        color = if (enabled) GlobalIETAColor.PrimaryText else GlobalIETAColor.MutedText
                    )
                )
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = if (enabled) animatedBorderColor else GlobalIETAColor.MutedText,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaButtonPreview() {
    GlobalIetaTheme {
        Row(modifier = Modifier.padding(16.dp)) {
            GlobalIetaButton(text = "LAUNCH CORE", onClick = {})
            Spacer(modifier = Modifier.width(12.dp))
            GlobalIetaOutlinedButton(text = "EXPLORE", onClick = {})
        }
    }
}
