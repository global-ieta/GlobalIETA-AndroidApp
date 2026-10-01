package com.example.ieta.feature.company

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
import androidx.compose.material.icons.rounded.BusinessCenter
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Work
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
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class LeadershipMember(
    val name: String,
    val role: String,
    val background: String
)

val leadersList = listOf(
    LeadershipMember("Dr. Alex Vance", "Chief Executive & Spatial Architect", "Ex-NASA / Quantum VR Research Lead"),
    LeadershipMember("Elena Rostova", "Head of AI & AURA Engineering", "MIT AI Lab Alum / Neural Net Specialist"),
    LeadershipMember("Marcus Sterling", "VP of Global Enterprise", "Former SAP / Enterprise Cloud Strategy")
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompanyScreen(
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
                text = "GLOBAL IETA CORP",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "COMPANY / ABOUT GLOBAL IETA",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Global IETA designs and deploys next-generation AI, software, and operational infrastructure for schools, businesses, and equestrian enterprises worldwide.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Mission Statement Card
        GlobalIetaCard(
            technicalLabel = "[ OUR MISSION & VISION ]",
            accentColor = GlobalIETAColor.PrimaryCyan
        ) {
            Text(
                text = "Empowering Organizations with Ambient Intelligence",
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "We believe technology should serve as a transparent operational mesh that empowers human decision making rather than replacing it.",
                style = GlobalIetaTheme.typography.body.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ LEADERSHIP TEAM ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        leadersList.forEach { leader ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(GlobalIETAColor.PrimaryCyan.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            tint = GlobalIETAColor.PrimaryCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = leader.name,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                        Text(
                            text = leader.role,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = GlobalIETAColor.PrimaryCyan
                            )
                        )
                        Text(
                            text = leader.background,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.MutedText
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Careers & Contact Section
        GlobalIetaCard(
            technicalLabel = "[ GLOBAL CAREERS & CONTACT ]",
            accentColor = GlobalIETAColor.ElectricBlue
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Work,
                    contentDescription = null,
                    tint = GlobalIETAColor.ElectricBlue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "BUILD THE SPATIAL FUTURE WITH US",
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Open roles in AI Research, Spatial Graphics Engineering, and Enterprise Operations.",
                style = GlobalIetaTheme.typography.body.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Contact: careers@global-ieta.io",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }
    }
}
