package com.example.ieta.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaLogo
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.cardPressSpring
import com.example.ieta.core.navigation.Screen
import com.example.ieta.feature.home.components.EcosystemGraph
import com.example.ieta.feature.home.components.HeroSection
import com.example.ieta.feature.home.components.MetricsSection
import kotlinx.coroutines.launch

data class HomeProductItem(
    val id: String,
    val name: String,
    val tagline: String,
    val description: String,
    val status: String,
    val statusColor: Color,
    val accentColor: Color,
    val route: String,
    val features: List<String>
)

val homeProducts = listOf(
    HomeProductItem(
        id = "aura",
        name = "AURA AI CORE",
        tagline = "Quantum AI & Neural Operator",
        description = "Context-aware AI operator executing complex workflows, deep analytics, and real-time natural language commands.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF00CFFF),
        route = Screen.Aura.route,
        features = listOf("Context Aware", "Real-Time NLP", "Autonomous Agents")
    ),
    HomeProductItem(
        id = "arin",
        name = "ARIN ENGINE",
        tagline = "Spatial Rendering & AR Core",
        description = "Unified spatial computing engine offering sub-millisecond pose tracking, real-time occlusion, and XR device sync.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF00CFFF),
        route = Screen.Arin.route,
        features = listOf("6DoF Spatial Pose", "Neural Mesh", "120 FPS Target")
    ),
    HomeProductItem(
        id = "aero",
        name = "AERO MOBILITY",
        tagline = "Aviation & Drone Fleet Telemetry",
        description = "Autonomous airspace orchestration with live 3D radar tracking, conflict prediction, and fleet command mesh.",
        status = "ENTERPRISE",
        statusColor = GlobalIETAColor.ElectricBlue,
        accentColor = Color(0xFF2578FF),
        route = Screen.Aero.route,
        features = listOf("ADS-B Fusion", "3D Radar", "Flight Path AI")
    ),
    HomeProductItem(
        id = "campus",
        name = "IETA CAMPUS",
        tagline = "Immersive Virtual University",
        description = "Full-scale virtual university campus environment for higher ed, surgical training, and engineering physics labs.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF2979FF),
        route = Screen.Campus.route,
        features = listOf("Tele-Presence Labs", "3D Physics Models", "AI Tutors")
    ),
    HomeProductItem(
        id = "connector",
        name = "IETA CONNECTOR",
        tagline = "Enterprise Interoperability Matrix",
        description = "High-speed API gateway bridging legacy SQL, ERP, and SAP databases with spatial metaverse operational nodes.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF00D4FF),
        route = Screen.Connector.route,
        features = listOf("Zero-Trust Security", "REST / gRPC / WebSockets", "< 0.8ms Latency")
    ),
    HomeProductItem(
        id = "rideos",
        name = "RIDEOS CORE",
        tagline = "Autonomous Fleet Operating System",
        description = "Real-time operating mesh for autonomous shuttles, delivery robotics, and urban transit fleets.",
        status = "EARLY ACCESS",
        statusColor = GlobalIETAColor.Warning,
        accentColor = Color(0xFF21C88A),
        route = Screen.RideOS.route,
        features = listOf("V2X Mesh", "Dynamic Dispatch", "LiDAR Fusion")
    ),
    HomeProductItem(
        id = "workspace",
        name = "IETA WORKSPACE",
        tagline = "Quantum Spatial Collaboration",
        description = "Spatial office suite bringing multi-screen spatial monitors, whiteboards, and digital twin avatars together.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF00CFFF),
        route = Screen.Workspace.route,
        features = listOf("Unlimited Screens", "Spatial Audio", "E2E Encryption")
    ),
    HomeProductItem(
        id = "marketplace",
        name = "GLOBAL MARKETPLACE",
        tagline = "DeCentralized Spatial Asset Exchange",
        description = "Secure asset trading floor for 3D models, digital twins, spatial licenses, and automated royalty smart contracts.",
        status = "CONCEPT",
        statusColor = GlobalIETAColor.MutedText,
        accentColor = Color(0xFFFF8A3D),
        route = Screen.Marketplace.route,
        features = listOf("3D Asset Exchange", "Smart Licensing", "Royalty Ledger")
    ),
    HomeProductItem(
        id = "gaming",
        name = "GAMING & INTERACTIVE",
        tagline = "Spatial Gaming & Metaverse Mesh",
        description = "High-performance multiplayer mesh and spatial physics engine for interactive entertainment and esports.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFF8B5CF6),
        route = Screen.Gaming.route,
        features = listOf("Low Latency Mesh", "Cross-Platform Sync", "Spatial Physics")
    ),
    HomeProductItem(
        id = "billing",
        name = "IETA BILLING",
        tagline = "Metered Subscriptions & License Matrix",
        description = "Unified subscription engine, usage-based billing, and enterprise software entitlement management.",
        status = "AVAILABLE",
        statusColor = GlobalIETAColor.Success,
        accentColor = Color(0xFFD9A93A),
        route = Screen.Billing.route,
        features = listOf("Metered Usage", "Entitlement Vault", "Global Taxes")
    )
)

data class IndustryHighlight(
    val name: String,
    val code: String,
    val description: String,
    val accentColor: Color
)

val industryHighlights = listOf(
    IndustryHighlight("Gaming & Interactive", "IND-01", "High-fidelity rendering and low-latency multiplayer mesh.", Color(0xFF8B5CF6)),
    IndustryHighlight("EduTech & Research", "IND-02", "Virtual university campuses and medical simulation labs.", Color(0xFF2979FF)),
    IndustryHighlight("Equine & Sports", "IND-03", "Biomechanical tracking and digital twin motion diagnostics.", Color(0xFF21C88A)),
    IndustryHighlight("Enterprise Ops", "IND-04", "Cross-platform data pipelines and quantum workspace.", Color(0xFF00D4FF)),
    IndustryHighlight("Global Marketplace", "IND-05", "DeCentralized spatial asset exchange and smart licensing.", Color(0xFFFF8A3D)),
    IndustryHighlight("Legal & Compliance", "IND-06", "Smart contract audit trails and regulatory guardrails.", Color(0xFFD9A93A))
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var ecosystemGraphYOffset by remember { mutableFloatStateOf(0f) }

    val footerExpandedState = remember { mutableStateMapOf<String, Boolean>() }

    // Entrance animation state
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
            .verticalScroll(scrollState)
            .padding(bottom = 32.dp)
    ) {
        // --- 1. HERO SECTION ---
        HeroSection(
            onExploreClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(ecosystemGraphYOffset.toInt())
                }
            },
            onMeetAuraClick = { onNavigateToRoute(Screen.Aura.route) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- QUICK TELEMETRY METRICS SECTION ---
        MetricsSection()

        Spacer(modifier = Modifier.height(28.dp))

        // --- 2. ECOSYSTEM GRAPH SECTION ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    ecosystemGraphYOffset = coordinates.positionInParent().y
                }
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = "[ GRAPH VISUALIZER // CORE TOPOLOGY ]",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontFamily = FontFamily.Monospace,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "INTERCONNECTED PLATFORM VERTICALS",
                style = GlobalIetaTheme.typography.sectionTitle.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            EcosystemGraph(onNavigateToRoute = onNavigateToRoute)
        }

        Spacer(modifier = Modifier.height(28.dp))

        // --- 3. PRODUCTS MATRIX SECTION ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "[ PRODUCT MATRIX // OPERATIONAL NODES ]",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontFamily = FontFamily.Monospace,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ENTERPRISE SOFTWARE & AI MATRIX",
                style = GlobalIetaTheme.typography.sectionTitle.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            homeProducts.forEachIndexed { index, product ->
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { 40 * (index + 1) })
                ) {
                    ProductMatrixCard(
                        product = product,
                        onNavigate = { onNavigateToRoute(product.route) }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // --- 4. INDUSTRIES HIGHLIGHT SECTION ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "[ ECOSYSTEM VERTICALS // INDUSTRY TARGETS ]",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontFamily = FontFamily.Monospace,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "SPECIALIZED INDUSTRY DEPLOYMENTS",
                style = GlobalIetaTheme.typography.sectionTitle.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                industryHighlights.forEach { ind ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.48f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GlobalIETAColor.DeepSurface)
                            .border(1.dp, ind.accentColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .clickable { onNavigateToRoute(Screen.Industries.route) }
                            .padding(14.dp)
                    ) {
                        Column {
                            Text(
                                text = ind.code,
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 9.sp,
                                    color = ind.accentColor
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = ind.name,
                                style = GlobalIetaTheme.typography.cardTitle.copy(
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = ind.description,
                                style = GlobalIetaTheme.typography.small.copy(
                                    color = GlobalIETAColor.MutedText
                                ),
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- 5. EARLY ACCESS BANNER ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, GlobalIETAColor.PrimaryCyan, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "[ EARLY ACCESS PROTOCOL ]",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontFamily = FontFamily.Monospace,
                        color = GlobalIETAColor.PrimaryCyan
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "JOIN THE GLOBAL IETA MATRIX",
                    style = GlobalIetaTheme.typography.hero.copy(
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Deploy next-generation AI, spatial computing, and business automation nodes directly into your organizational workflow.",
                    style = GlobalIetaTheme.typography.body.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                GlobalIetaButton(
                    text = "REQUEST ACCESS PROTOCOL",
                    onClick = { onNavigateToRoute(Screen.EarlyAccess.route) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // --- 6. MOBILE EXPANDABLE FOOTER ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GlobalIETAColor.SecondaryBg)
                .border(1.dp, GlobalIETAColor.Border)
                .padding(20.dp)
        ) {
            GlobalIetaLogo()

            Spacer(modifier = Modifier.height(16.dp))

            FooterAccordionGroup(
                title = "ECOSYSTEM",
                isExpanded = footerExpandedState["ECOSYSTEM"] ?: false,
                onToggle = { footerExpandedState["ECOSYSTEM"] = !(footerExpandedState["ECOSYSTEM"] ?: false) },
                items = listOf("AURA AI", "ARIN Engine", "AERO Mobility", "IETA Campus", "IETA Connector", "RideOS", "IETA Workspace", "Marketplace", "Gaming", "IETA Billing"),
                onItemClick = { item ->
                    val route = when (item) {
                        "AURA AI" -> Screen.Aura.route
                        "ARIN Engine" -> Screen.Arin.route
                        "AERO Mobility" -> Screen.Aero.route
                        "IETA Campus" -> Screen.Campus.route
                        "IETA Connector" -> Screen.Connector.route
                        "RideOS" -> Screen.RideOS.route
                        "IETA Workspace" -> Screen.Workspace.route
                        "Marketplace" -> Screen.Marketplace.route
                        "Gaming" -> Screen.Gaming.route
                        "IETA Billing" -> Screen.Billing.route
                        else -> Screen.Home.route
                    }
                    onNavigateToRoute(route)
                }
            )

            FooterAccordionGroup(
                title = "INDUSTRIES",
                isExpanded = footerExpandedState["INDUSTRIES"] ?: false,
                onToggle = { footerExpandedState["INDUSTRIES"] = !(footerExpandedState["INDUSTRIES"] ?: false) },
                items = listOf("Gaming & Interactive", "EduTech & Research", "Equine Analytics", "Enterprise Operations", "Global Marketplace", "Legal & Compliance"),
                onItemClick = { onNavigateToRoute(Screen.Industries.route) }
            )

            FooterAccordionGroup(
                title = "COMPANY",
                isExpanded = footerExpandedState["COMPANY"] ?: false,
                onToggle = { footerExpandedState["COMPANY"] = !(footerExpandedState["COMPANY"] ?: false) },
                items = listOf("About Global IETA", "Early Access Protocol", "Support Matrix"),
                onItemClick = { item ->
                    val route = when (item) {
                        "About Global IETA" -> Screen.Company.route
                        "Early Access Protocol" -> Screen.EarlyAccess.route
                        "Support Matrix" -> Screen.Support.route
                        else -> Screen.Home.route
                    }
                    onNavigateToRoute(route)
                }
            )

            FooterAccordionGroup(
                title = "RESOURCES & LEGAL",
                isExpanded = footerExpandedState["RESOURCES"] ?: false,
                onToggle = { footerExpandedState["RESOURCES"] = !(footerExpandedState["RESOURCES"] ?: false) },
                items = listOf("Developer Documentation", "System Specs", "Privacy Policy", "Terms of Service"),
                onItemClick = { item ->
                    val route = when (item) {
                        "Developer Documentation" -> Screen.Resources.route
                        "System Specs" -> Screen.About.route
                        "Privacy Policy" -> Screen.Privacy.route
                        "Terms of Service" -> Screen.Terms.route
                        else -> Screen.Home.route
                    }
                    onNavigateToRoute(route)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Operational Status Indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(GlobalIETAColor.Success)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ALL SYSTEMS OPERATIONAL // 99.999% MESH UPTIME",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = GlobalIETAColor.Success
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "© 2026 GLOBAL IETA CORP. ALL RIGHTS RESERVED.",
                style = GlobalIetaTheme.typography.small.copy(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = GlobalIETAColor.MutedText
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProductMatrixCard(
    product: HomeProductItem,
    onNavigate: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1.0f,
        animationSpec = cardPressSpring(),
        label = "ProductCardPressScale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(12.dp))
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onNavigate() }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Vertical accent bar on left
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(180.dp)
                    .background(product.accentColor)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    // Status Badge (● AVAILABLE, ● EARLY ACCESS, etc.)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(product.statusColor.copy(alpha = 0.15f))
                            .border(1.dp, product.statusColor, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "● ${product.status}",
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = product.statusColor
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = product.tagline,
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = product.accentColor
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    style = GlobalIetaTheme.typography.body.copy(
                        fontSize = 13.sp,
                        color = GlobalIETAColor.SecondaryText
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Feature Pills
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    product.features.forEach { feat ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(GlobalIETAColor.ElevatedSurface)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = feat,
                                style = GlobalIetaTheme.typography.small.copy(
                                    fontSize = 10.sp,
                                    color = GlobalIETAColor.SecondaryText
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LAUNCH PROTOCOL",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 11.sp,
                            color = product.accentColor
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "Navigate",
                        tint = product.accentColor,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FooterAccordionGroup(
    title: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, GlobalIETAColor.MutedBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 11.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
            Icon(
                imageVector = if (isExpanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                contentDescription = "Toggle",
                tint = GlobalIETAColor.SecondaryText,
                modifier = Modifier.size(18.dp)
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
                    .padding(bottom = 12.dp, start = 8.dp)
            ) {
                items.forEach { item ->
                    Text(
                        text = item,
                        style = GlobalIetaTheme.typography.body.copy(
                            fontSize = 13.sp,
                            color = GlobalIETAColor.SecondaryText
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item) }
                            .padding(vertical = 6.dp)
                    )
                }
            }
        }
    }
}
