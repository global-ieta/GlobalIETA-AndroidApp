package com.example.ieta.core.design

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object GlobalIETAColor {
    // 70-80% Dark Navy/Black Backgrounds & Deep Surfaces
    val PrimaryBackground = Color(0xFF020812)
    val SecondaryBackground = Color(0xFF06111D)
    val DeepSurface = Color(0xFF081522)
    val ElevatedSurface = Color(0xFF0B1928)

    // Aliases for backwards compatibility
    val PrimaryBg = PrimaryBackground
    val SecondaryBg = SecondaryBackground

    // Structural Borders
    val Border = Color(0xFF183247)
    val MutedBorder = Color(0xFF102536)

    // 15-20% Off-White Text Hierarchy
    val PrimaryText = Color(0xFFF4F7FA)
    val SecondaryText = Color(0xFFAAB9C9)
    val MutedText = Color(0xFF718398)

    // 5-10% Cyan Signal Colors (Used for Active / Interactive Indicators, NOT background fills)
    val PrimaryCyan = Color(0xFF00D9FF)
    val SecondaryCyan = Color(0xFF00A8D6)
    val ElectricBlue = Color(0xFF2578FF)
    val AuraBlue = Color(0xFF00CFFF)

    // Secondary Vertical Accents
    val GamingPurple = Color(0xFF8B5CF6)
    val EducationBlue = Color(0xFF2979FF)
    val EquineGreen = Color(0xFF21C88A)
    val BusinessCyan = Color(0xFF00D4FF)
    val MarketplaceOrange = Color(0xFFFF8A3D)
    val LegalGold = Color(0xFFD9A93A)

    // Status Signals
    val Success = Color(0xFF22C98B)
    val Warning = Color(0xFFF5B942)
    val Error = Color(0xFFFF5D6C)
}

@Immutable
data class GlobalIetaCustomColors(
    val primaryBg: Color = GlobalIETAColor.PrimaryBackground,
    val secondaryBg: Color = GlobalIETAColor.SecondaryBackground,
    val deepSurface: Color = GlobalIETAColor.DeepSurface,
    val elevatedSurface: Color = GlobalIETAColor.ElevatedSurface,
    val border: Color = GlobalIETAColor.Border,
    val mutedBorder: Color = GlobalIETAColor.MutedBorder,
    val primaryText: Color = GlobalIETAColor.PrimaryText,
    val secondaryText: Color = GlobalIETAColor.SecondaryText,
    val mutedText: Color = GlobalIETAColor.MutedText,
    val primaryCyan: Color = GlobalIETAColor.PrimaryCyan,
    val secondaryCyan: Color = GlobalIETAColor.SecondaryCyan,
    val electricBlue: Color = GlobalIETAColor.ElectricBlue,
    val auraBlue: Color = GlobalIETAColor.AuraBlue,
    val gamingPurple: Color = GlobalIETAColor.GamingPurple,
    val educationBlue: Color = GlobalIETAColor.EducationBlue,
    val equineGreen: Color = GlobalIETAColor.EquineGreen,
    val businessCyan: Color = GlobalIETAColor.BusinessCyan,
    val marketplaceOrange: Color = GlobalIETAColor.MarketplaceOrange,
    val legalGold: Color = GlobalIETAColor.LegalGold,
    val success: Color = GlobalIETAColor.Success,
    val warning: Color = GlobalIETAColor.Warning,
    val error: Color = GlobalIETAColor.Error
)

val LocalGlobalIetaColors = staticCompositionLocalOf { GlobalIetaCustomColors() }
