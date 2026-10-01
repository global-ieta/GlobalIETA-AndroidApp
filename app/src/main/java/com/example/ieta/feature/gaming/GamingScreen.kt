package com.example.ieta.feature.gaming

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class GamingPillar(
    val word: String,
    val description: String,
    val code: String
)

val equoraPillars = listOf(
    GamingPillar("PLAY", "Competitive equestrian sports, showjumping arenas, and multiplayer racing.", "PLR-01"),
    GamingPillar("LEARN", "Real biomechanical gait dynamics, horse care, and anatomy physics.", "LRN-02"),
    GamingPillar("BUILD", "Design custom riding arenas, breeding studs, and cross-country tracks.", "BLD-03"),
    GamingPillar("EXPERIENCE", "Photorealistic spatial XR environments powered by ARIN Engine.", "EXP-04")
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GamingScreen(
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
                .border(1.dp, GlobalIETAColor.GamingPurple, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.GamingPurple)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "GLOBAL IETA GAMING & EQUORA",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.GamingPurple
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "EQUORA - A WORLD BUILT AROUND THE HORSE.",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "PLAY. LEARN. BUILD. EXPERIENCE.",
            style = GlobalIetaTheme.typography.sectionTitle.copy(
                fontSize = 16.sp,
                color = GlobalIETAColor.GamingPurple
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "EQUORA is Global IETA's flag-ship spatial metaverse world combining equestrian sports, high-fidelity biomechanics simulation, and user-generated arena construction.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ CORE GAMING PILLARS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.GamingPurple
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4 Pillars Grid
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            equoraPillars.forEach { pillar ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.GamingPurple.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            text = pillar.code,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = GlobalIETAColor.GamingPurple
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = pillar.word,
                            style = GlobalIetaTheme.typography.hero.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = pillar.description,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.SecondaryText
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Multiplayer Mesh Specs
        GlobalIetaCard(
            technicalLabel = "[ SPATIAL PHYSICS ENGINE ]",
            accentColor = GlobalIETAColor.GamingPurple
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.SportsEsports,
                    contentDescription = null,
                    tint = GlobalIETAColor.GamingPurple,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "LOW-LATENCY MULTIPLAYER MESH",
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Sub-15ms global network sync powering 128-player simultaneous riding arenas.",
                        style = GlobalIetaTheme.typography.body.copy(
                            fontSize = 12.sp,
                            color = GlobalIETAColor.SecondaryText
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            GlobalIetaButton(
                text = "LAUNCH EQUORA DEMO NODE",
                onClick = { /* Launch demo */ },
                leadingIcon = Icons.Rounded.VideogameAsset,
                glowColor = GlobalIETAColor.GamingPurple,
                accentColor = GlobalIETAColor.GamingPurple,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
