package com.example.ieta.feature.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.ieta.core.components.GlobalIetaLogo
import com.example.ieta.core.components.GlobalIetaTextField
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.navigation.Screen

data class MenuItem(
    val title: String,
    val route: String,
    val technicalCode: String,
    val description: String? = null
)

data class MenuGroup(
    val categoryTitle: String,
    val categoryCode: String,
    val items: List<MenuItem>
)

val menuGroups = listOf(
    MenuGroup(
        categoryTitle = "ECOSYSTEM PLATFORMS",
        categoryCode = "CAT-ECO-01",
        items = listOf(
            MenuItem("AURA AI CORE", Screen.Aura.route, "NODE-AURA", "Quantum AI & Neural Operator"),
            MenuItem("ARIN ENGINE", Screen.Arin.route, "NODE-ARIN", "Spatial Rendering & AR Core"),
            MenuItem("AERO MOBILITY", Screen.Aero.route, "NODE-AERO", "Aviation & Drone Fleet Telemetry"),
            MenuItem("IETA CAMPUS", Screen.Campus.route, "NODE-CMP", "Immersive Virtual University"),
            MenuItem("IETA CONNECTOR", Screen.Connector.route, "NODE-CON", "Enterprise Interoperability Matrix"),
            MenuItem("RIDEOS CORE", Screen.RideOS.route, "NODE-RDS", "Autonomous Fleet OS"),
            MenuItem("IETA WORKSPACE", Screen.Workspace.route, "NODE-WSP", "Quantum Spatial Collaboration"),
            MenuItem("GLOBAL MARKETPLACE", Screen.Marketplace.route, "NODE-MKT", "DeCentralized Asset Exchange"),
            MenuItem("GAMING & INTERACTIVE", Screen.Gaming.route, "NODE-GAM", "Spatial Engine & Mesh"),
            MenuItem("IETA BILLING", Screen.Billing.route, "NODE-BIL", "Metered Subscriptions & Licensing")
        )
    ),
    MenuGroup(
        categoryTitle = "VERTICAL INDUSTRIES",
        categoryCode = "CAT-IND-02",
        items = listOf(
            MenuItem("Gaming & Interactive", Screen.Industries.route, "IND-GAM", "Metaverse & Esports Mesh"),
            MenuItem("EduTech & Research", Screen.Industries.route, "IND-EDU", "Virtual Campus & Simulation Labs"),
            MenuItem("Equine & Sports Analytics", Screen.Industries.route, "IND-EQU", "Biomechanical Tracking & Health"),
            MenuItem("Enterprise Operations", Screen.Industries.route, "IND-ENT", "Quantum Cloud & Interop Pipelines"),
            MenuItem("Global Marketplace", Screen.Industries.route, "IND-MKT", "Spatial Asset Licensing Floor"),
            MenuItem("Legal & Compliance", Screen.Industries.route, "IND-LGL", "Smart Contracts & Regulatory Guardrails")
        )
    ),
    MenuGroup(
        categoryTitle = "COMPANY & DEPLOYMENT",
        categoryCode = "CAT-CMP-03",
        items = listOf(
            MenuItem("About Global IETA", Screen.Company.route, "INFO-ABT", "Mission & Core Architecture"),
            MenuItem("Early Access Protocol", Screen.EarlyAccess.route, "INFO-EAP", "Request Organizational Node Access"),
            MenuItem("Support Matrix", Screen.Support.route, "INFO-SUP", "System Status & Support Tickets")
        )
    ),
    MenuGroup(
        categoryTitle = "RESOURCES & GOVERNANCE",
        categoryCode = "CAT-RES-04",
        items = listOf(
            MenuItem("Developer Documentation", Screen.Resources.route, "RES-DOCS", "API Gateways & SDK Docs"),
            MenuItem("System Specs", Screen.About.route, "RES-SPEC", "Node Hardware & Telemetry Specs"),
            MenuItem("Privacy Guardrails", Screen.Privacy.route, "GOV-PRIV", "Zero-Trust Data Protection"),
            MenuItem("Terms of Service", Screen.Terms.route, "GOV-TRM", "Platform Licensing Terms")
        )
    )
)

@Composable
fun MenuScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    onCloseMenu: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    val expandedGroups = remember {
        mutableStateMapOf<String, Boolean>().apply {
            put("CAT-ECO-01", true)
            put("CAT-IND-02", true)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Command Index Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "[ COMMAND INDEX // SYSTEM NAVIGATION ]",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        color = GlobalIETAColor.PrimaryCyan
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                GlobalIetaLogo(showTagline = false)
            }

            IconButton(
                onClick = onCloseMenu,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(8.dp))
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close Index",
                    tint = GlobalIETAColor.PrimaryCyan
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Auth & Profile Links Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.PrimaryCyan.copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                    .clickable { onNavigate(Screen.SignIn.route) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = null,
                        tint = GlobalIETAColor.PrimaryCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SIGN IN",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.PrimaryCyan
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(10.dp))
                    .clickable { onNavigate(Screen.Profile.route) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        tint = GlobalIETAColor.SecondaryText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ARCHITECT PROFILE",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.SecondaryText
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Command Filter Input
        GlobalIetaTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Filter operational nodes or verticals...",
            leadingIcon = Icons.Rounded.Search,
            label = "[ SEARCH PROTOCOL ]"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Collapsible Menu Groups
        menuGroups.forEach { group ->
            val matchingItems = group.items.filter {
                searchQuery.isBlank() ||
                        it.title.contains(searchQuery, ignoreCase = true) ||
                        it.technicalCode.contains(searchQuery, ignoreCase = true) ||
                        (it.description?.contains(searchQuery, ignoreCase = true) == true)
            }

            if (matchingItems.isNotEmpty()) {
                val isExpanded = expandedGroups[group.categoryCode] ?: (searchQuery.isNotBlank())

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedGroups[group.categoryCode] = !isExpanded
                                }
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "[ ${group.categoryCode} ]",
                                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                                        fontSize = 9.sp,
                                        color = GlobalIETAColor.PrimaryCyan
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = group.categoryTitle,
                                    style = GlobalIetaTheme.typography.cardTitle.copy(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            }

                            Icon(
                                imageVector = if (isExpanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                                contentDescription = "Toggle Group",
                                tint = GlobalIETAColor.PrimaryCyan
                            )
                        }

                        AnimatedVisibility(
                            visible = isExpanded,
                            enter = expandVertically() + fadeIn(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 4.dp)
                            ) {
                                matchingItems.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable { onNavigate(item.route) }
                                            .padding(vertical = 10.dp, horizontal = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    text = item.technicalCode,
                                                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                                                        fontSize = 9.sp,
                                                        color = GlobalIETAColor.ElectricBlue
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
                                            if (item.description != null) {
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Text(
                                                    text = item.description,
                                                    style = GlobalIetaTheme.typography.small.copy(
                                                        color = GlobalIETAColor.MutedText
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
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
