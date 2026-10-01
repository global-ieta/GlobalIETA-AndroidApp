package com.example.ieta.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorScheme = darkColorScheme(
    primary = GlobalIETAColor.PrimaryCyan,
    onPrimary = GlobalIETAColor.PrimaryBackground,
    primaryContainer = GlobalIETAColor.DeepSurface,
    onPrimaryContainer = GlobalIETAColor.PrimaryCyan,
    secondary = GlobalIETAColor.ElectricBlue,
    onSecondary = GlobalIETAColor.PrimaryText,
    secondaryContainer = GlobalIETAColor.ElevatedSurface,
    onSecondaryContainer = GlobalIETAColor.AuraBlue,
    tertiary = GlobalIETAColor.GamingPurple,
    onTertiary = GlobalIETAColor.PrimaryText,
    tertiaryContainer = GlobalIETAColor.DeepSurface,
    onTertiaryContainer = GlobalIETAColor.GamingPurple,
    background = GlobalIETAColor.PrimaryBackground,
    onBackground = GlobalIETAColor.PrimaryText,
    surface = GlobalIETAColor.SecondaryBackground,
    onSurface = GlobalIETAColor.PrimaryText,
    surfaceVariant = GlobalIETAColor.DeepSurface,
    onSurfaceVariant = GlobalIETAColor.SecondaryText,
    outline = GlobalIETAColor.Border,
    outlineVariant = GlobalIETAColor.MutedBorder,
    error = GlobalIETAColor.Error,
    onError = GlobalIETAColor.PrimaryBackground
)

@Composable
fun GlobalIetaTheme(
    colors: GlobalIetaCustomColors = GlobalIetaCustomColors(),
    typography: GlobalIetaTypography = GlobalIetaTypography(),
    shapes: GlobalIetaShapes = GlobalIetaShapes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalGlobalIetaColors provides colors,
        LocalGlobalIetaTypography provides typography,
        LocalGlobalIetaShapes provides shapes
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            content = content
        )
    }
}

object GlobalIetaTheme {
    val colors: GlobalIetaCustomColors
        @Composable
        get() = LocalGlobalIetaColors.current

    val typography: GlobalIetaTypography
        @Composable
        get() = LocalGlobalIetaTypography.current

    val shapes: GlobalIetaShapes
        @Composable
        get() = LocalGlobalIetaShapes.current
}
