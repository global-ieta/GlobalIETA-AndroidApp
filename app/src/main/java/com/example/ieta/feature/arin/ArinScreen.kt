package com.example.ieta.feature.arin

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
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.ViewInAr
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

data class ArinAgeBand(
    val title: String,
    val stage: String,
    val ageRange: String,
    val description: String,
    val features: List<String>,
    val icon: ImageVector
)

val arinAgeBands = listOf(
    ArinAgeBand(
        title = "TODDLER - 3",
        stage = "DISCOVER",
        ageRange = "Ages 1 - 3",
        description = "Sensory spatial exploration, color and shape tactile recognition, and interactive 3D audio-visual play.",
        features = listOf("Tactile Spatial Play", "Audio-Visual Feedback", "Parent Guided Mode"),
        icon = Icons.Rounded.ChildCare
    ),
    ArinAgeBand(
        title = "AGES 4 - 6",
        stage = "IMAGINE",
        ageRange = "Ages 4 - 6",
        description = "Imaginative world construction, elementary spatial logic, gesture interaction, and storybook spatial mesh.",
        features = listOf("Gesture Sandbox", "Storybook Spatial Mesh", "Spatial Geometry Basics"),
        icon = Icons.Rounded.Lightbulb
    ),
    ArinAgeBand(
        title = "AGES 7 - 10",
        stage = "GROW",
        ageRange = "Ages 7 - 10",
        description = "Core STEM concepts, interactive 3D physics puzzles, early spatial coding, and collaborative learning rooms.",
        features = listOf("3D Physics Sandbox", "Visual Block Coding", "Multi-Student Rooms"),
        icon = Icons.Rounded.Explore
    ),
    ArinAgeBand(
        title = "AGE 10+",
        stage = "HANDOFF TO AERO",
        ageRange = "Age 10+",
        description = "Smooth transition to advanced education, engineering physics, flight mechanics, and AERO mobility protocols.",
        features = listOf("AERO Bridge Protocol", "Advanced Physics Models", "Career Discovery"),
        icon = Icons.Rounded.School
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArinScreen(
    onNavigateToAero: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedBandIndex by remember { mutableStateOf(0) }
    val currentBand = arinAgeBands[selectedBandIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Header
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
                text = "EARLY LEARNING SPATIAL ENGINE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "ARIN / SMALL STEPS. BIG BEGINNINGS.",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "ARIN provides early childhood spatial learning and cognitive engine tools designed to foster curiosity, spatial intelligence, and creative problem solving from infancy through age 10.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ AGE STAGE NAVIGATION ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Age Navigation Cards
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            arinAgeBands.forEachIndexed { index, band ->
                val isSelected = index == selectedBandIndex

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GlobalIETAColor.ElevatedSurface else GlobalIETAColor.DeepSurface)
                        .border(
                            1.dp,
                            if (isSelected) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.Border,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedBandIndex = index }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = band.stage,
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 10.sp,
                                    color = if (isSelected) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.MutedText
                                )
                            )
                            Icon(
                                imageVector = band.icon,
                                contentDescription = null,
                                tint = if (isSelected) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.SecondaryText,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = band.title,
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

        // Stage Detail Display Card
        AnimatedContent(
            targetState = currentBand,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "AgeBandDetail"
        ) { band ->
            GlobalIetaCard(
                technicalLabel = "[ ARIN // STAGE: ${band.stage} ]",
                accentColor = GlobalIETAColor.PrimaryCyan
            ) {
                Text(
                    text = "${band.title} (${band.ageRange})",
                    style = GlobalIetaTheme.typography.sectionTitle.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = band.description,
                    style = GlobalIetaTheme.typography.body.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "CORE LEARNING MODULES:",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        color = GlobalIETAColor.PrimaryCyan
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                band.features.forEach { feat ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(GlobalIETAColor.PrimaryCyan)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = feat,
                            style = GlobalIetaTheme.typography.body.copy(
                                fontSize = 13.sp,
                                color = GlobalIETAColor.PrimaryText
                            )
                        )
                    }
                }

                if (band.stage == "HANDOFF TO AERO") {
                    Spacer(modifier = Modifier.height(16.dp))
                    GlobalIetaButton(
                        text = "CONTINUE TO AERO MOBILITY",
                        onClick = onNavigateToAero,
                        trailingIcon = Icons.AutoMirrored.Rounded.ArrowForward,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Hardware & XR Specs
        GlobalIetaCard(
            technicalLabel = "[ SPATIAL SPECIFICATIONS ]",
            accentColor = GlobalIETAColor.ElectricBlue
        ) {
            Text(
                text = "Child-Safe Spatial Computing Guardrails",
                style = GlobalIetaTheme.typography.cardTitle
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Sub-2.4ms spatial pose alignment, automated screen-time ergonomics, eye-tracking safety bounds, and end-to-end parental consent controls.",
                style = GlobalIetaTheme.typography.body
            )
        }
    }
}
