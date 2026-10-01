package com.example.ieta.feature.home.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaBottomSheet
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaLogoIcon
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.cyanGlow
import com.example.ieta.core.design.nodeSelectionSpring
import com.example.ieta.core.navigation.Screen
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

data class EcosystemNode(
    val id: String,
    val name: String,
    val category: String,
    val route: String,
    val accentColor: Color,
    val description: String,
    val code: String,
)

val ecosystemNodes = listOf(
    EcosystemNode(
        id = "aura",
        name = "AURA",
        category = "AI & Intelligence",
        route = Screen.Aura.route,
        accentColor = Color(0xFF00CFFF),
        description = "Quantum AI Core & Neural Operator powering context-aware cross-platform automation.",
        code = "NODE-AI-01"
    ),
    EcosystemNode(
        id = "education",
        name = "EDUCATION",
        category = "EduTech & Research",
        route = Screen.Campus.route,
        accentColor = Color(0xFF2979FF),
        description = "IETA CAMPUS virtual university with immersive surgical, engineering, and physics simulation labs.",
        code = "NODE-EDU-02"
    ),
    EcosystemNode(
        id = "business",
        name = "BUSINESS",
        category = "Enterprise Operations",
        route = Screen.Workspace.route,
        accentColor = Color(0xFF00D4FF),
        description = "IETA WORKSPACE & CONNECTOR bridging legacy IT infrastructure with quantum spatial workflows.",
        code = "NODE-ENT-03"
    ),
    EcosystemNode(
        id = "legal",
        name = "LEGAL",
        category = "Legal & Compliance",
        route = Screen.Industries.route,
        accentColor = Color(0xFFD9A93A),
        description = "Smart contract audit trails, IP registration matrix, and global cross-border compliance guardrails.",
        code = "NODE-LGL-04"
    ),
    EcosystemNode(
        id = "gaming",
        name = "GAMING",
        category = "Gaming & Interactive",
        route = Screen.Gaming.route,
        accentColor = Color(0xFF8B5CF6),
        description = "High-fidelity spatial engine and low-latency multiplayer mesh for interactive metaverse apps.",
        code = "NODE-GAM-05"
    ),
    EcosystemNode(
        id = "equine",
        name = "EQUINE",
        category = "Equine & Sports Analytics",
        route = Screen.Industries.route,
        accentColor = Color(0xFF21C88A),
        description = "Biomechanical gait tracking, motion telemetry, and digital twin health diagnostic systems.",
        code = "NODE-EQU-06"
    ),
    EcosystemNode(
        id = "marketplace",
        name = "MARKETPLACE",
        category = "Global Marketplace",
        route = Screen.Marketplace.route,
        accentColor = Color(0xFFFF8A3D),
        description = "Decentralized spatial asset exchange, digital twins, and automated licensing protocol.",
        code = "NODE-MKT-07"
    )
)

val centralNode = EcosystemNode(
    id = "ieta",
    name = "GLOBAL IETA",
    category = "Platform Core",
    route = Screen.Home.route,
    accentColor = GlobalIETAColor.PrimaryCyan,
    description = "Central neural ecosystem uniting AI engines, spatial computing, and business automation platforms.",
    code = "CORE-00"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcosystemGraph(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedNode by remember { mutableStateOf<EcosystemNode?>(null) }

    // Dash phase animation over 4500ms
    val infiniteTransition = rememberInfiniteTransition(label = "graphLineAnimation")
    val dashPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "dashPhase"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(16.dp))
        ) {
            val widthPx = constraints.maxWidth.toFloat()
            val heightPx = constraints.maxHeight.toFloat()
            val centerX = widthPx / 2f
            val centerY = heightPx / 2f

            val graphRadius = ((widthPx.coerceAtMost(heightPx) / 2f) - 48.dp.value)
                .coerceAtLeast(100f)

            // Animated Canvas lines and radial background glow
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Background radial mesh glow
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            GlobalIETAColor.PrimaryCyan.copy(alpha = 0.12f),
                            GlobalIETAColor.DeepSurface
                        ),
                        center = Offset(centerX, centerY),
                        radius = graphRadius * 1.25f
                    ),
                    center = Offset(centerX, centerY),
                    radius = graphRadius * 1.25f
                )

                // Concentric orbit rings
                drawCircle(
                    color = GlobalIETAColor.Border.copy(alpha = 0.5f),
                    radius = graphRadius,
                    center = Offset(centerX, centerY),
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                )

                drawCircle(
                    color = GlobalIETAColor.MutedBorder.copy(alpha = 0.35f),
                    radius = graphRadius * 0.55f,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 1.dp.toPx())
                )

                // Moving dash phase connection lines
                ecosystemNodes.forEachIndexed { index, node ->
                    val angle = (2.0 * Math.PI * index / ecosystemNodes.size) - (Math.PI / 2.0)
                    val nodeX = centerX + (graphRadius * cos(angle)).toFloat()
                    val nodeY = centerY + (graphRadius * sin(angle)).toFloat()

                    val isSelected = selectedNode?.id == node.id
                    val lineColor = if (isSelected) node.accentColor else Color(0xFF00CFFF).copy(alpha = 0.35f)
                    val strokeWidthPx = if (isSelected) 2.5.dp.toPx() else 1.2.dp.toPx()

                    drawLine(
                        color = lineColor,
                        start = Offset(centerX, centerY),
                        end = Offset(nodeX, nodeY),
                        strokeWidth = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(12f, 8f),
                            dashPhase
                        )
                    )

                    // Glow behind node
                    if (isSelected) {
                        drawCircle(
                            color = node.accentColor.copy(alpha = 0.4f),
                            radius = 28.dp.toPx(),
                            center = Offset(nodeX, nodeY)
                        )
                    }
                }
            }

            // Central Node: GLOBAL IETA
            val isCentralSelected = selectedNode?.id == centralNode.id
            val centralScale by animateFloatAsState(
                targetValue = if (isCentralSelected) 1.10f else 1.0f,
                animationSpec = nodeSelectionSpring(),
                label = "centralNodeScale"
            )
            val centralAlpha by animateFloatAsState(
                targetValue = if (selectedNode != null && !isCentralSelected) 0.5f else 1.0f,
                animationSpec = tween(250),
                label = "centralNodeAlpha"
            )

            val centralSize = 78.dp

            Box(
                modifier = Modifier
                    .size(centralSize)
                    .offset {
                        IntOffset(
                            (centerX - (centralSize.value * density / 2)).roundToInt(),
                            (centerY - (centralSize.value * density / 2)).roundToInt()
                        )
                    }
                    .graphicsLayer {
                        scaleX = centralScale
                        scaleY = centralScale
                        this.alpha = centralAlpha
                    }
                    .then(
                        if (isCentralSelected) {
                            Modifier.cyanGlow(
                                color = GlobalIETAColor.PrimaryCyan,
                                blurRadius = 14.dp,
                                alpha = 0.6f,
                                cornerRadius = 39.dp
                            )
                        } else Modifier
                    )
                    .clip(CircleShape)
                    .background(GlobalIETAColor.SecondaryBg)
                    .border(2.dp, GlobalIETAColor.PrimaryCyan, CircleShape)
                    .clickable { selectedNode = centralNode },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(4.dp)
                ) {
                    GlobalIetaLogoIcon(size = 26.dp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "GLOBAL IETA",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            // Peripheral Nodes Overlay
            ecosystemNodes.forEachIndexed { index, node ->
                val angle = (2.0 * Math.PI * index / ecosystemNodes.size) - (Math.PI / 2.0)
                val nodeX = centerX + (graphRadius * cos(angle)).toFloat()
                val nodeY = centerY + (graphRadius * sin(angle)).toFloat()

                val nodeSize = 56.dp
                val isSelected = selectedNode?.id == node.id

                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.10f else 1.0f,
                    animationSpec = nodeSelectionSpring(),
                    label = "nodeScale_${node.id}"
                )

                val alpha by animateFloatAsState(
                    targetValue = if (selectedNode != null && !isSelected) 0.50f else 1.0f,
                    animationSpec = tween(250),
                    label = "nodeAlpha_${node.id}"
                )

                Box(
                    modifier = Modifier
                        .size(nodeSize)
                        .offset {
                            IntOffset(
                                (nodeX - (nodeSize.value * density / 2)).roundToInt(),
                                (nodeY - (nodeSize.value * density / 2)).roundToInt()
                            )
                        }
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .then(
                            if (isSelected) {
                                Modifier.cyanGlow(
                                    color = node.accentColor,
                                    blurRadius = 14.dp,
                                    alpha = 0.6f,
                                    cornerRadius = 28.dp
                                )
                            } else Modifier
                        )
                        .clip(CircleShape)
                        .background(if (isSelected) node.accentColor.copy(alpha = 0.25f) else GlobalIETAColor.ElevatedSurface)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) node.accentColor else node.accentColor.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                        .clickable { selectedNode = node },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = node.name,
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else node.accentColor,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.PrimaryCyan)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "TAP ANY NODE TO INSPECT VERTICAL PROTOCOL",
                style = GlobalIetaTheme.typography.small.copy(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = GlobalIETAColor.MutedText
                )
            )
        }

        // Node bottom sheet
        selectedNode?.let { node ->
            GlobalIetaBottomSheet(
                onDismissRequest = { selectedNode = null },
                title = node.name,
                technicalLabel = "[ ECOSYSTEM NODE // ${node.code} ]"
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GlobalIETAColor.DeepSurface)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        // Vertical color indicator
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(22.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(node.accentColor)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(node.accentColor.copy(alpha = 0.2f))
                                .border(1.dp, node.accentColor, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = node.category.uppercase(),
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 10.sp,
                                    color = node.accentColor
                                )
                            )
                        }
                    }

                    Text(
                        text = node.description,
                        style = GlobalIetaTheme.typography.body.copy(
                            color = GlobalIETAColor.SecondaryText
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    GlobalIetaButton(
                        text = if (node.id == "ieta") "OPEN CORE" else "OPEN ${node.name}",
                        onClick = {
                            val targetRoute = node.route
                            selectedNode = null
                            onNavigateToRoute(targetRoute)
                        },
                        accentColor = node.accentColor,
                        glowColor = node.accentColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
