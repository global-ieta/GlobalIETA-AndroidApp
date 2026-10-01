package com.example.ieta.feature.support

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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Api
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.ViewInAr
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.components.GlobalIetaDropdown
import com.example.ieta.core.components.GlobalIetaTextField
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.domain.model.TicketPriority

data class SupportCategoryItem(
    val name: String,
    val code: String,
    val icon: ImageVector
)

val supportCategoryList = listOf(
    SupportCategoryItem("ARIN Engine", "CAT-ARIN", Icons.Rounded.ViewInAr),
    SupportCategoryItem("AURA AI Core", "CAT-AURA", Icons.Rounded.AutoAwesome),
    SupportCategoryItem("API & Gateway", "CAT-API", Icons.Rounded.Api),
    SupportCategoryItem("Billing & Licensing", "CAT-BIL", Icons.Rounded.CreditCard),
    SupportCategoryItem("Hardware & Headsets", "CAT-HW", Icons.Rounded.Memory)
)

val categoryNamesList = supportCategoryList.map { it.name }
val priorityOptionsList = listOf("LOW", "MEDIUM", "HIGH", "CRITICAL")

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SupportScreen(
    modifier: Modifier = Modifier,
    viewModel: SupportViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()

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
                text = "GLOBAL SUPPORT MATRIX & DISPATCH",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "SUPPORT MATRIX",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Direct technical support channel connecting spatial architects with Global IETA engineering leads.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Create Ticket CTA Button
        GlobalIetaButton(
            text = "CREATE SUPPORT REQUEST",
            onClick = { viewModel.openCreateTicketModal() },
            leadingIcon = Icons.Rounded.Add,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ SUPPORT CATEGORIES ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            supportCategoryList.forEach { cat ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = cat.icon,
                            contentDescription = null,
                            tint = GlobalIETAColor.PrimaryCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = cat.name,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "[ MY REQUESTS & TICKETS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        uiState.tickets.forEach { ticket ->
            GlobalIetaCard(
                technicalLabel = "[ ${ticket.ticketNumber} // ${ticket.category.uppercase()} ]",
                accentColor = when (ticket.priority) {
                    TicketPriority.HIGH, TicketPriority.CRITICAL -> GlobalIETAColor.Warning
                    else -> GlobalIETAColor.PrimaryCyan
                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ticket.subject,
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(GlobalIETAColor.ElectricBlue.copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = ticket.status.name,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 8.sp,
                                color = GlobalIETAColor.ElectricBlue
                            )
                        )
                    }
                }

                if (ticket.messages.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = ticket.messages.last().text,
                        style = GlobalIetaTheme.typography.body.copy(
                            fontSize = 12.sp,
                            color = GlobalIETAColor.SecondaryText
                        ),
                        maxLines = 2
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    // Modal Sheet for Creating Support Request
    if (uiState.isCreatingTicket) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.closeCreateTicketModal() },
            sheetState = sheetState,
            containerColor = GlobalIETAColor.DeepSurface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "[ DISPATCH TECHNICAL TICKET ]",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        color = GlobalIETAColor.PrimaryCyan
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                GlobalIetaTextField(
                    value = uiState.subjectInput,
                    onValueChange = { viewModel.onSubjectChange(it) },
                    label = "Issue Subject",
                    placeholder = "Brief title describing the issue..."
                )

                Spacer(modifier = Modifier.height(12.dp))

                GlobalIetaDropdown(
                    label = "Category",
                    selectedOption = uiState.categoryInput,
                    options = categoryNamesList,
                    onOptionSelected = { viewModel.onCategoryChange(it) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                GlobalIetaDropdown(
                    label = "Priority Level",
                    selectedOption = uiState.priorityInput.name,
                    options = priorityOptionsList,
                    onOptionSelected = {
                        val prio = when (it) {
                            "HIGH" -> TicketPriority.HIGH
                            "CRITICAL" -> TicketPriority.CRITICAL
                            "LOW" -> TicketPriority.LOW
                            else -> TicketPriority.MEDIUM
                        }
                        viewModel.onPriorityChange(prio)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                GlobalIetaTextField(
                    value = uiState.descriptionInput,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    label = "Description & Telemetry Logs",
                    placeholder = "Describe the steps to reproduce or paste error codes...",
                    singleLine = false
                )

                Spacer(modifier = Modifier.height(20.dp))

                GlobalIetaButton(
                    text = "DISPATCH TICKET",
                    onClick = { viewModel.createTicket() },
                    isLoading = uiState.isSubmitting,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
