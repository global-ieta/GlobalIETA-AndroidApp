package com.example.ieta.feature.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Security
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

data class SystemNotificationLog(
    val id: String,
    val title: String,
    val time: String,
    val message: String,
    val type: String,
    val color: Color
)

val mockNotifications = listOf(
    SystemNotificationLog("LOG-901", "AURA Neural Model Updated to v4.2", "12 mins ago", "Context window expanded to 128k tokens with sub-2.4ms spatial response times.", "SYSTEM", GlobalIETAColor.AuraBlue),
    SystemNotificationLog("LOG-902", "New Parent Notice Posted in CONNECTOR", "1 hour ago", "Parent-Teacher Conference timetable is now live on the Parent Channel.", "COMMS", GlobalIETAColor.PrimaryCyan),
    SystemNotificationLog("LOG-903", "RideOS Gait Telemetry Audit Complete", "3 hours ago", "Dressage Arena 1 horses reported 100% nominal gait alignment.", "EQUINE", GlobalIETAColor.EquineGreen),
    SystemNotificationLog("LOG-904", "Zero-Trust Security Key Renewal Required", "Yesterday", "Please confirm passkey signature for USR-001 before Nov 01, 2026.", "SECURITY", GlobalIETAColor.Warning)
)

@Composable
fun NotificationsScreen(
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
                text = "TELEMETRY LOGS & NOTIFICATIONS",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "SYSTEM LOGS & NOTIFICATIONS",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        mockNotifications.forEach { notif ->
            GlobalIetaCard(
                technicalLabel = "[ ${notif.type} // ${notif.time.uppercase()} ]",
                accentColor = notif.color
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notif.title,
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = notif.message,
                    style = GlobalIetaTheme.typography.body.copy(
                        fontSize = 12.sp,
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
