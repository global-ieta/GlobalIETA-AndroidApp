package com.example.ieta.feature.connector

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
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.FamilyRestroom
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.School
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
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class ConnectorChannel(
    val title: String,
    val code: String,
    val countLabel: String,
    val icon: ImageVector,
    val color: Color
)

val connectorChannels = listOf(
    ConnectorChannel("Announcements", "CHN-ANN", "3 New Broadcasts", Icons.Rounded.Notifications, GlobalIETAColor.PrimaryCyan),
    ConnectorChannel("Direct Messages", "CHN-MSG", "12 Active Threads", Icons.AutoMirrored.Rounded.Message, GlobalIETAColor.AuraBlue),
    ConnectorChannel("Student Updates", "CHN-STD", "4 Academic Posts", Icons.Rounded.School, GlobalIETAColor.EducationBlue),
    ConnectorChannel("Parent Channels", "CHN-PRN", "8 Parent Queries", Icons.Rounded.FamilyRestroom, GlobalIETAColor.ElectricBlue)
)

data class MockNotice(
    val title: String,
    val author: String,
    val time: String,
    val snippet: String,
    val channelCode: String
)

val mockNotices = listOf(
    MockNotice("Annual STEM & Spatial XR Exhibition Scheduled", "Dr. Vance", "10 mins ago", "All Grade 9-12 students are invited to submit their ARIN 3D physics projects by Friday.", "CHN-ANN"),
    MockNotice("Parent-Teacher Conference Timetable Released", "Academic Dean", "1 hour ago", "Slot bookings are now live on the Parent Portal for Q3 progress reviews.", "CHN-PRN"),
    MockNotice("Mid-Term Physics Exam Results Published", "Prof. Hawking", "3 hours ago", "Individual grade breakdowns and spatial lab feedback uploaded to Student Vault.", "CHN-STD"),
    MockNotice("Equine Biomechanics Workshop This Saturday", "Coach Miller", "Yesterday", "RIDEOS gait tracking sensors will be demonstrated at the main arena.", "CHN-MSG")
)

@Composable
fun ConnectorScreen(
    modifier: Modifier = Modifier
) {
    var selectedChannelCode by remember { mutableStateOf("CHN-ANN") }

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
                .border(1.dp, GlobalIETAColor.BusinessCyan, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.BusinessCyan)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "INSTITUTIONAL COMMS MATRIX",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.BusinessCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "CONNECTOR / INSTITUTIONAL COMMUNICATIONS",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "High-speed encrypted communication hub linking school administrators, teachers, students, and parents into unified broadcast channels.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ COMMUNICATION CHANNELS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.BusinessCyan
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Channel Selector Cards
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            connectorChannels.forEach { ch ->
                val isSelected = ch.code == selectedChannelCode

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GlobalIETAColor.ElevatedSurface else GlobalIETAColor.DeepSurface)
                        .border(
                            1.dp,
                            if (isSelected) ch.color else GlobalIETAColor.Border,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedChannelCode = ch.code }
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(ch.color.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = ch.icon,
                                contentDescription = null,
                                tint = ch.color,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = ch.title,
                                style = GlobalIetaTheme.typography.cardTitle.copy(
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = ch.countLabel,
                                style = GlobalIetaTheme.typography.small.copy(
                                    color = if (isSelected) ch.color else GlobalIETAColor.MutedText
                                )
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "[ RECENT CHANNEL BROADCASTS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.BusinessCyan
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Notices List
        mockNotices.forEach { notice ->
            GlobalIetaCard(
                technicalLabel = "[ ${notice.channelCode} // ${notice.time.uppercase()} ]",
                accentColor = GlobalIETAColor.BusinessCyan
            ) {
                Text(
                    text = notice.title,
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        fontSize = 15.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "AUTHOR: ${notice.author.uppercase()}",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 9.sp,
                        color = GlobalIETAColor.SecondaryText
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = notice.snippet,
                    style = GlobalIetaTheme.typography.body.copy(
                        fontSize = 13.sp,
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
