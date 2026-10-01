package com.example.ieta.feature.workspace

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Assignment
import androidx.compose.material.icons.rounded.AutoFixHigh
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.Work
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

data class WorkspaceTask(
    val id: String,
    val title: String,
    val category: String,
    val assignee: String,
    val priority: String,
    val status: String,
    val description: String
)

val mockTasks = listOf(
    WorkspaceTask(
        id = "TSK-101",
        title = "ARIN Engine Pose Tracking Calibration",
        category = "Projects",
        assignee = "Dr. Vance",
        priority = "CRITICAL",
        status = "IN PROGRESS",
        description = "Optimize sub-2.4ms pose alignment for multi-user spatial headset anchors over CONNECTOR API gateway."
    ),
    WorkspaceTask(
        id = "TSK-102",
        title = "Campus Q3 Attendance Ledger Audit",
        category = "Documents",
        assignee = "Elena Rostova",
        priority = "HIGH",
        status = "PENDING REVIEW",
        description = "Reconcile student biometric attendance records across 42 active spatial classrooms."
    ),
    WorkspaceTask(
        id = "TSK-103",
        title = "RideOS Equine Gait Telemetry Sync",
        category = "Automation",
        assignee = "Coach Miller",
        priority = "MEDIUM",
        status = "COMPLETED",
        description = "Verify real-time IMU sensor data streams from Dressage Arena 1 into digital twin analytics."
    ),
    WorkspaceTask(
        id = "TSK-104",
        title = "Marketplace Smart Contract Audit",
        category = "Communication",
        assignee = "Legal Guardrails",
        priority = "HIGH",
        status = "IN PROGRESS",
        description = "Validate 3D spatial asset licensing and automated royalty distribution smart contracts."
    )
)

data class WorkspaceCategory(
    val name: String,
    val count: String,
    val icon: ImageVector
)

val workspaceCategories = listOf(
    WorkspaceCategory("Tasks", "14 Active", Icons.AutoMirrored.Rounded.Assignment),
    WorkspaceCategory("Documents", "42 Docs", Icons.Rounded.Description),
    WorkspaceCategory("Projects", "8 Live", Icons.Rounded.Folder),
    WorkspaceCategory("Team", "24 Members", Icons.Rounded.Group),
    WorkspaceCategory("Communication", "6 Channels", Icons.Rounded.Work),
    WorkspaceCategory("Automation", "12 Flows", Icons.Rounded.AutoFixHigh)
)

@Composable
fun WorkspaceScreen(
    modifier: Modifier = Modifier
) {
    var selectedTaskIndex by remember { mutableStateOf(0) }
    val selectedTask = mockTasks[selectedTaskIndex]

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
    ) {
        val isWideScreen = maxWidth >= 600.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.AuraBlue, RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(GlobalIETAColor.AuraBlue)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ENTERPRISE OPERATIONS WORKSPACE",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = GlobalIETAColor.AuraBlue
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "IETA WORKSPACE / ENTERPRISE OPERATIONS",
                style = GlobalIetaTheme.typography.hero.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            if (isWideScreen) {
                // Adaptive 2-Column Layout for Tablet/Wide Screen
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Left Column: Task List
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "[ ENTERPRISE TASKS & OPERATIONS ]",
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                color = GlobalIETAColor.AuraBlue
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            itemsIndexed(mockTasks) { index, task ->
                                TaskItemCard(
                                    task = task,
                                    isSelected = index == selectedTaskIndex,
                                    onClick = { selectedTaskIndex = index }
                                )
                            }
                        }
                    }

                    // Right Column: Workspace Task Detail
                    Box(
                        modifier = Modifier
                            .weight(1.2f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp))
                            .background(GlobalIETAColor.DeepSurface)
                            .border(1.dp, GlobalIETAColor.AuraBlue.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        WorkspaceDetailPanel(task = selectedTask)
                    }
                }
            } else {
                // Single Column Layout for Phone Screen
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "[ OPERATIONAL MODULES ]",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.AuraBlue
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        workspaceCategories.take(3).forEach { cat ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(GlobalIETAColor.DeepSurface)
                                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text(
                                        text = cat.name,
                                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                                            fontSize = 9.sp,
                                            color = GlobalIETAColor.AuraBlue
                                        )
                                    )
                                    Text(
                                        text = cat.count,
                                        style = GlobalIetaTheme.typography.cardTitle.copy(
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "[ ACTIVE TASKS LIST ]",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.AuraBlue
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    mockTasks.forEachIndexed { index, task ->
                        TaskItemCard(
                            task = task,
                            isSelected = index == selectedTaskIndex,
                            onClick = { selectedTaskIndex = index }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "[ SELECTED TASK DETAIL ]",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.AuraBlue
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    GlobalIetaCard(
                        technicalLabel = "[ ${selectedTask.id} // ${selectedTask.priority} ]",
                        accentColor = GlobalIETAColor.AuraBlue
                    ) {
                        WorkspaceDetailPanel(task = selectedTask)
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskItemCard(
    task: WorkspaceTask,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) GlobalIETAColor.ElevatedSurface else GlobalIETAColor.DeepSurface)
            .border(
                1.dp,
                if (isSelected) GlobalIETAColor.AuraBlue else GlobalIETAColor.Border,
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.id,
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = GlobalIETAColor.AuraBlue
                    )
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when (task.priority) {
                                "CRITICAL" -> GlobalIETAColor.Error.copy(alpha = 0.2f)
                                "HIGH" -> GlobalIETAColor.Warning.copy(alpha = 0.2f)
                                else -> GlobalIETAColor.Success.copy(alpha = 0.2f)
                            }
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = task.priority,
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 8.sp,
                            color = when (task.priority) {
                                "CRITICAL" -> GlobalIETAColor.Error
                                "HIGH" -> GlobalIETAColor.Warning
                                else -> GlobalIETAColor.Success
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = task.title,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 14.sp,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ASSIGNEE: ${task.assignee}",
                    style = GlobalIetaTheme.typography.small.copy(
                        color = GlobalIETAColor.MutedText
                    )
                )
                Text(
                    text = task.status,
                    style = GlobalIetaTheme.typography.small.copy(
                        color = GlobalIETAColor.AuraBlue
                    )
                )
            }
        }
    }
}

@Composable
private fun WorkspaceDetailPanel(task: WorkspaceTask) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = GlobalIETAColor.AuraBlue,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${task.id} WORKSPACE DETAILS",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 12.sp,
                    color = GlobalIETAColor.AuraBlue
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = task.title,
            style = GlobalIetaTheme.typography.sectionTitle.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column {
                Text(
                    text = "CATEGORY",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 9.sp,
                        color = GlobalIETAColor.MutedText
                    )
                )
                Text(
                    text = task.category,
                    style = GlobalIetaTheme.typography.body.copy(
                        color = Color.White
                    )
                )
            }

            Column {
                Text(
                    text = "ASSIGNEE",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 9.sp,
                        color = GlobalIETAColor.MutedText
                    )
                )
                Text(
                    text = task.assignee,
                    style = GlobalIetaTheme.typography.body.copy(
                        color = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "OPERATIONAL SPECIFICATION:",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.AuraBlue
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = task.description,
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(GlobalIETAColor.ElevatedSurface)
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "QUANTUM AUTOMATION PIPELINE",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        fontSize = 10.sp,
                        color = GlobalIETAColor.Success
                    )
                )
                Text(
                    text = "AURA neural agent is monitoring task telemetry. Automated status reports scheduled every 6 operational hours.",
                    style = GlobalIetaTheme.typography.small.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            }
        }
    }
}
