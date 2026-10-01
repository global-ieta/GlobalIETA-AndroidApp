package com.example.ieta.feature.industries

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
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.BusinessCenter
import androidx.compose.material.icons.rounded.Gavel
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.SportsEsports
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

data class IndustryVertical(
    val id: String,
    val title: String,
    val code: String,
    val tagline: String,
    val description: String,
    val color: Color,
    val icon: ImageVector,
    val targetRoute: String
)

val industryVerticals = listOf(
    IndustryVertical(
        id = "education",
        title = "Education & Research",
        code = "IND-EDU-01",
        tagline = "Campus & Virtual Labs",
        description = "Immersive academic simulation labs, virtual university campuses, and AI tutoring systems.",
        color = GlobalIETAColor.EducationBlue,
        icon = Icons.Rounded.School,
        targetRoute = "campus"
    ),
    IndustryVertical(
        id = "business",
        title = "Enterprise Business",
        code = "IND-BUS-02",
        tagline = "API Connectors & Automation",
        description = "High-speed zero-trust API gateways bridging legacy ERP databases with spatial metaverse nodes.",
        color = GlobalIETAColor.BusinessCyan,
        icon = Icons.Rounded.BusinessCenter,
        targetRoute = "workspace"
    ),
    IndustryVertical(
        id = "legal",
        title = "Legal & Compliance",
        code = "IND-LGL-03",
        tagline = "Smart Contract Audit Trails",
        description = "Automated digital asset licensing, IP registry, and regulatory compliance guardrails.",
        color = GlobalIETAColor.LegalGold,
        icon = Icons.Rounded.Gavel,
        targetRoute = "billing"
    ),
    IndustryVertical(
        id = "equine",
        title = "Equine & Sports",
        code = "IND-EQU-04",
        tagline = "Biomechanical Telemetry",
        description = "Digital twin equine health tracking, gait diagnostics, and riding school operations.",
        color = GlobalIETAColor.EquineGreen,
        icon = Icons.Rounded.Pets,
        targetRoute = "rideos"
    ),
    IndustryVertical(
        id = "gaming",
        title = "Gaming & Interactive",
        code = "IND-GAM-05",
        tagline = "Low-Latency Metaverse Mesh",
        description = "High-performance spatial physics engine and multiplayer mesh for interactive entertainment.",
        color = GlobalIETAColor.GamingPurple,
        icon = Icons.Rounded.SportsEsports,
        targetRoute = "gaming"
    ),
    IndustryVertical(
        id = "ai",
        title = "AI & Automation",
        code = "IND-AI-06",
        tagline = "Quantum Neural Operator",
        description = "Context-aware AI operators executing complex workflows, NLP commands, and automated agents.",
        color = GlobalIETAColor.PrimaryCyan,
        icon = Icons.Rounded.AutoAwesome,
        targetRoute = "aura"
    )
)

@Composable
fun IndustriesScreen(
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
                text = "ECOSYSTEM VERTICALS & DEPLOYMENT TARGETS",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "INDUSTRIES / VERTICAL DEPLOYMENTS",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tailored software architecture and AI operators engineered specifically for key industry domains.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Vertical Cards
        industryVerticals.forEach { ind ->
            GlobalIetaCard(
                technicalLabel = "[ ${ind.code} // ${ind.tagline.uppercase()} ]",
                accentColor = ind.color,
                onClick = { onNavigateToRoute(ind.targetRoute) }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(ind.color.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ind.icon,
                            contentDescription = null,
                            tint = ind.color,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ind.title,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = ind.description,
                            style = GlobalIetaTheme.typography.body.copy(
                                fontSize = 12.sp,
                                color = GlobalIETAColor.SecondaryText
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "EXPLORE VERTICAL NODE",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 10.sp,
                            color = ind.color
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "Navigate",
                        tint = ind.color,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
