package com.example.ieta.feature.aero

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.FlightTakeoff
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class AeroProgressionStep(
    val stage: String,
    val ageRange: String,
    val title: String,
    val description: String,
    val skills: List<String>,
    val icon: ImageVector
)

val aeroSteps = listOf(
    AeroProgressionStep(
        stage = "EXPLORE",
        ageRange = "Ages 10 - 13",
        title = "AERODYNAMICS & SIMULATION",
        description = "Discover fundamental physics, flight dynamics, virtual wind tunnel testing, and drone telemetry simulation.",
        skills = listOf("Virtual Wind Tunnel", "3D Physics Mechanics", "Basic Telemetry Data"),
        icon = Icons.Rounded.Explore
    ),
    AeroProgressionStep(
        stage = "BUILD",
        ageRange = "Ages 14 - 17",
        title = "DRONE & ROBOTICS ARCHITECTURE",
        description = "CAD spatial modeling, flight controller programming, autonomous path navigation algorithms, and sensor fusion.",
        skills = listOf("CAD Spatial Modeling", "Autonomous Flight Algorithms", "Sensor Fusion Debugging"),
        icon = Icons.Rounded.Build
    ),
    AeroProgressionStep(
        stage = "LAUNCH",
        ageRange = "Ages 18 - 21",
        title = "AIRSPACE & FLEET ORCHESTRATION",
        description = "Enterprise ADS-B integration, live 3D radar monitoring, UTM airspace management, and certified pilot protocols.",
        skills = listOf("ADS-B Radar Mesh", "UTM Fleet Dispatch", "Certification Protocols"),
        icon = Icons.Rounded.RocketLaunch
    ),
    AeroProgressionStep(
        stage = "ADVANCE",
        ageRange = "Ages 22 - 25",
        title = "GLOBAL AVIATION LEADERSHIP",
        description = "Advanced autonomous fleet control, AI traffic conflict prediction, aerospace entrepreneurship, and global operations.",
        skills = listOf("AI Traffic Prediction", "Aerospace Entrepreneurship", "Global Operations Mesh"),
        icon = Icons.Rounded.TrendingUp
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AeroScreen(
    onNavigateToCampus: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedStepIndex by remember { mutableStateOf(0) }
    val currentStep = aeroSteps[selectedStepIndex]

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
                .border(1.dp, GlobalIETAColor.ElectricBlue, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.ElectricBlue)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "EDUCATION & MOBILITY PROGRESSION",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.ElectricBlue
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "AERO / LEARN TODAY. BUILD TOMORROW.",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "AERO bridges education and mobility tech, taking students from early aerodynamics concepts through to enterprise drone fleet management and aerospace engineering.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ AGE TIMELINE PROGRESSION ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.ElectricBlue
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Step Timeline Selection Bar
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            aeroSteps.forEachIndexed { index, step ->
                val isSelected = index == selectedStepIndex

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GlobalIETAColor.ElevatedSurface else GlobalIETAColor.DeepSurface)
                        .border(
                            1.dp,
                            if (isSelected) GlobalIETAColor.ElectricBlue else GlobalIETAColor.Border,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedStepIndex = index }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = step.stage,
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 10.sp,
                                    color = if (isSelected) GlobalIETAColor.ElectricBlue else GlobalIETAColor.MutedText
                                )
                            )
                            Icon(
                                imageVector = step.icon,
                                contentDescription = null,
                                tint = if (isSelected) GlobalIETAColor.ElectricBlue else GlobalIETAColor.SecondaryText,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = step.ageRange,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Step Detail Display Card
        AnimatedContent(
            targetState = currentStep,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "AeroStepDetail"
        ) { step ->
            GlobalIetaCard(
                technicalLabel = "[ AERO // PROGRESSION: ${step.stage} ]",
                accentColor = GlobalIETAColor.ElectricBlue
            ) {
                Text(
                    text = "${step.title} (${step.ageRange})",
                    style = GlobalIetaTheme.typography.sectionTitle.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = step.description,
                    style = GlobalIetaTheme.typography.body.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ACQUIRED COMPETENCIES:",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        color = GlobalIETAColor.ElectricBlue
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                step.skills.forEach { skill ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(GlobalIETAColor.ElectricBlue)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = skill,
                            style = GlobalIetaTheme.typography.body.copy(
                                fontSize = 13.sp,
                                color = GlobalIETAColor.PrimaryText
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Autonomous Airspace Radar Simulation
        GlobalIetaCard(
            technicalLabel = "[ TELEMETRY RADAR SIMULATOR ]",
            accentColor = GlobalIETAColor.PrimaryCyan
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.FlightTakeoff,
                    contentDescription = null,
                    tint = GlobalIETAColor.PrimaryCyan,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "12,450 ACTIVE CRAFT IN MESH",
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            color = Color.White
                        )
                    )
                    Text(
                        text = "100% Safety record across connected airspace sectors.",
                        style = GlobalIetaTheme.typography.small.copy(
                            color = GlobalIETAColor.Success
                        )
                    )
                }
            }
        }
    }
}
