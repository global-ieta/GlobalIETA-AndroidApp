package com.example.ieta.feature.settings

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.components.GlobalIetaDropdown
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

val themeOptionsList = listOf("Dark Protocol (Default)", "Light Mode", "System Default")
val languageOptionsList = listOf("English (US)", "Spanish", "French", "German", "Japanese")

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    var selectedTheme by remember { mutableStateOf(themeOptionsList[0]) }
    var soundFxEnabled by remember { mutableStateOf(false) } // Default OFF
    var animationsEnabled by remember { mutableStateOf(true) }
    var privacyAnalyticsEnabled by remember { mutableStateOf(false) }
    var biometricAuthEnabled by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf(languageOptionsList[0]) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, GlobalIETAColor.PrimaryCyan, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.PrimaryCyan)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "SYSTEM PREFERENCES & CONFIGURATION",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "SETTINGS / SYSTEM CONFIGURATION",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Appearance & Theme Card
        GlobalIetaCard(
            technicalLabel = "[ APPEARANCE & THEME ]",
            accentColor = GlobalIETAColor.PrimaryCyan
        ) {
            GlobalIetaDropdown(
                label = "UI Theme Mode",
                selectedOption = selectedTheme,
                options = themeOptionsList,
                onOptionSelected = { selectedTheme = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            GlobalIetaDropdown(
                label = "Interface Language",
                selectedOption = selectedLanguage,
                options = languageOptionsList,
                onOptionSelected = { selectedLanguage = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // System Toggles Card
        GlobalIetaCard(
            technicalLabel = "[ AUDIO & ANIMATION PREFERENCES ]",
            accentColor = GlobalIETAColor.AuraBlue
        ) {
            SettingSwitchRow(
                title = "Audio Sound FX",
                subtitle = "Play tactile feedback sounds for AURA and menu commands (Default OFF).",
                checked = soundFxEnabled,
                onCheckedChange = { soundFxEnabled = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingSwitchRow(
                title = "UI Motion & Animations",
                subtitle = "Enable high-fps spring animations and spatial glow effects.",
                checked = animationsEnabled,
                onCheckedChange = { animationsEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Security & Privacy Card
        GlobalIetaCard(
            technicalLabel = "[ SECURITY & PRIVACY GUARDRAILS ]",
            accentColor = GlobalIETAColor.ElectricBlue
        ) {
            SettingSwitchRow(
                title = "Biometric Passkey Authentication",
                subtitle = "Require fingerprint/face unlock for spatial node access.",
                checked = biometricAuthEnabled,
                onCheckedChange = { biometricAuthEnabled = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingSwitchRow(
                title = "Zero-Trust Anonymous Analytics",
                subtitle = "Share anonymized telemetry to improve AURA quantum models.",
                checked = privacyAnalyticsEnabled,
                onCheckedChange = { privacyAnalyticsEnabled = it }
            )
        }
    }
}

@Composable
private fun SettingSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = GlobalIetaTheme.typography.small.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = GlobalIETAColor.PrimaryBg,
                checkedTrackColor = GlobalIETAColor.PrimaryCyan,
                uncheckedThumbColor = GlobalIETAColor.MutedText,
                uncheckedTrackColor = GlobalIETAColor.DeepSurface
            )
        )
    }
}
