package com.example.ieta.feature.rideos

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
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Person
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

data class RideOSCategory(
    val name: String,
    val count: String,
    val status: String,
    val icon: ImageVector
)

val rideOSCategories = listOf(
    RideOSCategory("Bookings", "24 Scheduled", "Today", Icons.Rounded.CalendarMonth),
    RideOSCategory("Attendance", "98% Recorded", "On Track", Icons.Rounded.CheckCircle),
    RideOSCategory("Students", "148 Riders", "Active", Icons.Rounded.Group),
    RideOSCategory("Instructors", "12 Certified", "Duty Shift", Icons.Rounded.Person),
    RideOSCategory("Horses", "32 Equine Twins", "Healthy", Icons.Rounded.Pets),
    RideOSCategory("Finance", "$42.8k Revenue", "Q3 Ledger", Icons.Rounded.AttachMoney),
    RideOSCategory("Reports", "Gait & Biomechanics", "PDF Ready", Icons.Rounded.Analytics)
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RideOSScreen(
    modifier: Modifier = Modifier
) {
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    val selectedCat = rideOSCategories[selectedCategoryIndex]

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
                .border(1.dp, GlobalIETAColor.EquineGreen, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.EquineGreen)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "EQUINE OPERATIONS & BIOMECHANICS PLATFORM",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.EquineGreen
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "RIDEOS / EQUINE OPERATING SYSTEM",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Complete equestrian management platform powering riding schools, breeding studs, equestrian centers, and biomechanical performance tracking.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ OPERATIONAL MODULES ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.EquineGreen
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Module Selection Grid
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            rideOSCategories.forEachIndexed { index, cat ->
                val isSelected = index == selectedCategoryIndex

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GlobalIETAColor.ElevatedSurface else GlobalIETAColor.DeepSurface)
                        .border(
                            1.dp,
                            if (isSelected) GlobalIETAColor.EquineGreen else GlobalIETAColor.Border,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedCategoryIndex = index }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cat.name.uppercase(),
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    fontSize = 10.sp,
                                    color = if (isSelected) GlobalIETAColor.EquineGreen else GlobalIETAColor.MutedText
                                )
                            )
                            Icon(
                                imageVector = cat.icon,
                                contentDescription = null,
                                tint = if (isSelected) GlobalIETAColor.EquineGreen else GlobalIETAColor.SecondaryText,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = cat.count,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = cat.status,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.EquineGreen
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Selected Module Workspace Detail
        GlobalIetaCard(
            technicalLabel = "[ RIDEOS WORKSPACE // ${selectedCat.name.uppercase()} ]",
            accentColor = GlobalIETAColor.EquineGreen
        ) {
            Text(
                text = "${selectedCat.name} Overview & Live Telemetry",
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Real-time sync enabled for ${selectedCat.name}. Biomechanical sensors report 100% nominal gait alignment and heart-rate recovery telemetry across all registered equine digital twins.",
                style = GlobalIetaTheme.typography.body.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(GlobalIETAColor.ElevatedSurface)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEXT ARENA SESSION: DRESSAGE ARENA 1",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = GlobalIETAColor.EquineGreen
                    )
                )
                Text(
                    text = "14:30 EST",
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}
