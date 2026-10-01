package com.example.ieta.feature.campus

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
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.FamilyRestroom
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
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

data class CampusStatCardItem(
    val title: String,
    val statValue: String,
    val subtitle: String,
    val accentColor: Color,
    val icon: ImageVector
)

val campusStats = listOf(
    CampusStatCardItem("Students", "1,240 Active", "98.2% Enrolled", GlobalIETAColor.EducationBlue, Icons.Rounded.Group),
    CampusStatCardItem("Teachers", "85 Faculty", "42 Departments", GlobalIETAColor.PrimaryCyan, Icons.Rounded.Person),
    CampusStatCardItem("Attendance", "96.4%", "+1.2% this week", GlobalIETAColor.Success, Icons.Rounded.CheckCircle),
    CampusStatCardItem("Fees", "$142,500", "88% Collected", GlobalIETAColor.LegalGold, Icons.Rounded.AttachMoney),
    CampusStatCardItem("Classes", "42 Active", "Spatial XR Enabled", GlobalIETAColor.AuraBlue, Icons.AutoMirrored.Rounded.MenuBook),
    CampusStatCardItem("Exams", "8 Scheduled", "Mid-term Protocol", GlobalIETAColor.Warning, Icons.Rounded.Assignment),
    CampusStatCardItem("Parents", "1,100 Linked", "Parent Portal Mesh", GlobalIETAColor.ElectricBlue, Icons.Rounded.FamilyRestroom),
    CampusStatCardItem("Communication", "18 Unread", "Broadcast Channels", GlobalIETAColor.GamingPurple, Icons.AutoMirrored.Rounded.Message)
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CampusScreen(
    onNavigateToConnector: () -> Unit = {},
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
                .border(1.dp, GlobalIETAColor.EducationBlue, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.EducationBlue)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "SCHOOL OPERATIONS WORKSPACE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.EducationBlue
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "IETA CAMPUS / SCHOOL DASHBOARD",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Unified operational workspace for managing students, faculty, academics, fees, examinations, and institutional communications.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ CORE OPERATIONAL METRICS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.EducationBlue
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Grid of Stats
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            campusStats.forEach { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, item.accentColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.title,
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 10.sp,
                                    color = item.accentColor
                                )
                            )
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = item.accentColor,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = item.statValue,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = item.subtitle,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.MutedText
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Institutional Comms Card
        GlobalIetaCard(
            technicalLabel = "[ INSTITUTIONAL COMMS LINK ]",
            accentColor = GlobalIETAColor.GamingPurple,
            onClick = onNavigateToConnector
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.School,
                    contentDescription = null,
                    tint = GlobalIETAColor.GamingPurple,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "IETA CONNECTOR / PARENT & STUDENT CHANNELS",
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Access live institutional announcements, parent messages, and grade releases.",
                        style = GlobalIetaTheme.typography.body.copy(
                            fontSize = 12.sp,
                            color = GlobalIETAColor.SecondaryText
                        )
                    )
                }
            }
        }
    }
}
