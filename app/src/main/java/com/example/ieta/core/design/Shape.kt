package com.example.ieta.core.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class GlobalIetaShapes(
    val small: Shape = RoundedCornerShape(4.dp),
    val medium: Shape = RoundedCornerShape(8.dp),
    val large: Shape = RoundedCornerShape(14.dp),
    val card: Shape = RoundedCornerShape(12.dp),
    val button: Shape = RoundedCornerShape(8.dp),
    val pill: Shape = RoundedCornerShape(50)
)

val LocalGlobalIetaShapes = staticCompositionLocalOf { GlobalIetaShapes() }
