package com.example.ieta.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.components.GlobalIetaOutlinedButton
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

val connectedNodesList = listOf(
    "AURA AI CORE", "ARIN ENGINE", "IETA CONNECTOR", "RIDEOS CORE", "IETA WORKSPACE"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    onSignOut: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    modifier: Modifier = Modifier
) {
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
                text = "SYSTEM ARCHITECT PROFILE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Header Card
        GlobalIetaCard(
            technicalLabel = "[ CREDENTIAL IDENTIFIER // USR-001 ]",
            accentColor = GlobalIETAColor.PrimaryCyan
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(GlobalIETAColor.PrimaryCyan.copy(alpha = 0.2f))
                        .border(1.dp, GlobalIETAColor.PrimaryCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                        tint = GlobalIETAColor.PrimaryCyan,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Dr. Alex Vance",
                        style = GlobalIetaTheme.typography.sectionTitle.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Chief Spatial Architect • Nexus Global Technologies",
                        style = GlobalIetaTheme.typography.body.copy(
                            fontSize = 12.sp,
                            color = GlobalIETAColor.SecondaryText
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "alex.vance@global-ieta.io",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 10.sp,
                            color = GlobalIETAColor.PrimaryCyan
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(GlobalIETAColor.ElevatedSurface)
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SUBSCRIPTION TIER",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 9.sp,
                            color = GlobalIETAColor.MutedText
                        )
                    )
                    Text(
                        text = "GLOBAL CORE PLATINUM",
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontSize = 12.sp,
                            color = GlobalIETAColor.LegalGold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ CONNECTED SPATIAL NODES ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            connectedNodesList.forEach { node ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.Success.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = GlobalIETAColor.Success,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = node,
                            style = GlobalIetaTheme.typography.small.copy(
                                fontSize = 11.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ ACCOUNT & SECURITY SETTINGS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Settings items
        ProfileSettingItem(
            title = "Account Preferences & Theme",
            subtitle = "Theme, Sound FX, Animations, and Language",
            icon = Icons.Rounded.Security,
            onClick = onNavigateToSettings
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProfileSettingItem(
            title = "Hardware Security Keys & Passkeys",
            subtitle = "3 Registered FIDO2 Quantum Security Keys",
            icon = Icons.Rounded.Key,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProfileSettingItem(
            title = "Notification Channel Subscriptions",
            subtitle = "Encrypted push alerts & system telemetry logs",
            icon = Icons.Rounded.Notifications,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        GlobalIetaOutlinedButton(
            text = "SIGN OUT OF GLOBAL IETA",
            onClick = onSignOut,
            leadingIcon = Icons.AutoMirrored.Rounded.Logout,
            borderColor = GlobalIETAColor.Error,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ProfileSettingItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GlobalIETAColor.PrimaryCyan,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = subtitle,
                    style = GlobalIetaTheme.typography.small.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            }
        }
    }
}
