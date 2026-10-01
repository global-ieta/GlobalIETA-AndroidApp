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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun GlobalIetaCard(
    modifier: Modifier = Modifier,
    technicalLabel: String? = null,
    accentColor: Color = GlobalIETAColor.PrimaryCyan,
    showAccentLine: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && onClick != null) 0.985f else 1.0f,
        animationSpec = tween(durationMillis = 120, easing = FastOutSlowInEasing),
        label = "CardPressScale"
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = if (isPressed && onClick != null) accentColor else GlobalIETAColor.Border,
        animationSpec = tween(durationMillis = 120, easing = FastOutSlowInEasing),
        label = "CardBorderColor"
    )

    val shape = RoundedCornerShape(16.dp)

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, animatedBorderColor, shape)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onClick() }
                } else Modifier
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            if (showAccentLine || technicalLabel != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showAccentLine) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(16.dp)
                                .clip(RoundedCornerShape(1.5.dp))
                                .background(accentColor)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    if (technicalLabel != null) {
                        Text(
                            text = technicalLabel,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                color = accentColor
                            )
                        )
                    }
                }
            }

            content()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaCardPreview() {
    GlobalIetaTheme {
        GlobalIetaCard(
            technicalLabel = "[ ARIN // ENGINE ]",
            accentColor = GlobalIETAColor.PrimaryCyan,
            onClick = {}
        ) {
            Text(
                text = "Next-Gen Spatial Computing",
                style = GlobalIetaTheme.typography.cardTitle
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Unified spatial render engine powering interconnected XR instances across devices.",
                style = GlobalIetaTheme.typography.body
            )
        }
    }
}
