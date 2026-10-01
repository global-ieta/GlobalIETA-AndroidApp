package com.example.ieta.feature.resources

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Api
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.PrivacyTip
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
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class ResourceLinkItem(
    val title: String,
    val code: String,
    val description: String,
    val icon: ImageVector,
    val targetRoute: String
)

val resourceItems = listOf(
    ResourceLinkItem("Help Centre", "DOC-01", "Knowledge base, FAQs, and troubleshooting guides.", Icons.Rounded.Help, "support"),
    ResourceLinkItem("Product Guides", "DOC-02", "Setup guides for AURA, ARIN Engine, RideOS, and Campus.", Icons.Rounded.Description, "about"),
    ResourceLinkItem("API Gateways & SDKs", "DOC-03", "CONNECTOR REST / gRPC API references and spatial SDKs.", Icons.Rounded.Api, "resources"),
    ResourceLinkItem("Security Whitepaper", "DOC-04", "Zero-Trust architecture and encryption protocols.", Icons.Rounded.Security, "about"),
    ResourceLinkItem("System Status Matrix", "DOC-05", "Live node health telemetry and server status.", Icons.Rounded.CheckCircle, "about"),
    ResourceLinkItem("Privacy Guardrails", "DOC-06", "Data compliance, GDPR, and parental consent bounds.", Icons.Rounded.PrivacyTip, "privacy"),
    ResourceLinkItem("Terms of Service", "DOC-07", "Software licensing agreements and SLA terms.", Icons.Rounded.Lock, "terms")
)

@Composable
fun ResourcesScreen(
    onNavigateToRoute: (String) -> Unit = {},
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
                text = "DEVELOPER DOCUMENTATION & RESOURCES",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "RESOURCES & DOCUMENTATION HUB",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Comprehensive documentation, API specs, security whitepapers, and operational guides for Global IETA products.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Resource Links
        resourceItems.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                    .clickable { onNavigateToRoute(item.targetRoute) }
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(GlobalIETAColor.PrimaryCyan.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = GlobalIETAColor.PrimaryCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = item.code,
                                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                                        fontSize = 9.sp,
                                        color = GlobalIETAColor.PrimaryCyan
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = item.title,
                                    style = GlobalIetaTheme.typography.cardTitle.copy(
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.description,
                                style = GlobalIetaTheme.typography.small.copy(
                                    color = GlobalIETAColor.SecondaryText
                                )
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = "Navigate",
                        tint = GlobalIETAColor.MutedText,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}
