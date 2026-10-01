package com.example.ieta.feature.earlyaccess

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
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaCheckbox
import com.example.ieta.core.components.GlobalIetaDropdown
import com.example.ieta.core.components.GlobalIetaTextField
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

val availableProductsList = listOf(
    "AURA AI", "ARIN Engine", "AERO Mobility",
    "IETA Campus", "IETA Connector", "RideOS",
    "IETA Workspace", "Marketplace", "Gaming"
)

val usageIntentOptions = listOf(
    "K-12 School / Education",
    "Equine Riding School / Academy",
    "Enterprise Business Ops",
    "Research Lab / University",
    "Personal / Developer Node"
)

val orgSizeOptions = listOf(
    "1 - 50",
    "51 - 200",
    "201 - 1,000",
    "1,000+"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EarlyAccessScreen(
    modifier: Modifier = Modifier,
    viewModel: EarlyAccessViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var mobileInput by remember { mutableStateOf("") }
    var countryInput by remember { mutableStateOf("United States") }
    var roleInput by remember { mutableStateOf("") }
    var selectedUsageIntent by remember { mutableStateOf(usageIntentOptions[0]) }
    var selectedOrgSize by remember { mutableStateOf(orgSizeOptions[0]) }
    var messageInput by remember { mutableStateOf("") }
    var consentChecked by remember { mutableStateOf(false) }

    val selectedProducts = remember { mutableStateListOf<String>("AURA AI", "ARIN Engine") }

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
                text = "EARLY ACCESS PROTOCOL",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "REQUEST ORGANIZATIONAL ACCESS",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Join the Global IETA operational matrix. Submit your organization credentials to request early access node allocation.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Form Fields
        GlobalIetaTextField(
            value = uiState.nameInput,
            onValueChange = { viewModel.onNameChange(it) },
            label = "Full Name",
            placeholder = "e.g. Dr. Alex Vance",
            isError = uiState.errorMessage != null && uiState.nameInput.isBlank()
        )

        Spacer(modifier = Modifier.height(12.dp))

        GlobalIetaTextField(
            value = uiState.emailInput,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email Address",
            placeholder = "alex.vance@organization.io",
            isError = uiState.errorMessage != null && uiState.emailInput.isBlank()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            GlobalIetaTextField(
                value = mobileInput,
                onValueChange = { mobileInput = it },
                label = "Mobile Number",
                placeholder = "+1 555-0199",
                modifier = Modifier.weight(1f)
            )

            GlobalIetaTextField(
                value = countryInput,
                onValueChange = { countryInput = it },
                label = "Country",
                placeholder = "United States",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            GlobalIetaTextField(
                value = uiState.organizationInput,
                onValueChange = { viewModel.onOrgChange(it) },
                label = "Organisation",
                placeholder = "e.g. Nexus Academy",
                modifier = Modifier.weight(1f)
            )

            GlobalIetaTextField(
                value = roleInput,
                onValueChange = { roleInput = it },
                label = "Role / Title",
                placeholder = "e.g. Director",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Products of Interest Checkboxes Grid
        Text(
            text = "PRODUCTS OF INTEREST",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.SecondaryText
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            availableProductsList.forEach { prod ->
                val isChecked = selectedProducts.contains(prod)
                GlobalIetaCheckbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        if (checked) selectedProducts.add(prod) else selectedProducts.remove(prod)
                    },
                    label = prod
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            GlobalIetaDropdown(
                label = "Usage Intent",
                selectedOption = selectedUsageIntent,
                options = usageIntentOptions,
                onOptionSelected = { selectedUsageIntent = it },
                modifier = Modifier.weight(1.2f)
            )

            GlobalIetaDropdown(
                label = "Org Size",
                selectedOption = selectedOrgSize,
                options = orgSizeOptions,
                onOptionSelected = { selectedOrgSize = it },
                modifier = Modifier.weight(0.8f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        GlobalIetaTextField(
            value = messageInput,
            onValueChange = { messageInput = it },
            label = "Message / Specific Requirements",
            placeholder = "Describe your spatial or operational deployment goals...",
            singleLine = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalIetaCheckbox(
            checked = consentChecked,
            onCheckedChange = { consentChecked = it },
            label = "I consent to Global IETA processing my organizational request under Zero-Trust data guardrails."
        )

        Spacer(modifier = Modifier.height(20.dp))

        GlobalIetaButton(
            text = "SUBMIT EARLY ACCESS REQUEST",
            onClick = {
                viewModel.submitRequest(
                    mobile = mobileInput,
                    country = countryInput,
                    role = roleInput,
                    selectedProducts = selectedProducts.toList(),
                    orgSize = selectedOrgSize,
                    message = messageInput
                )
            },
            enabled = consentChecked && !uiState.isSubmitting,
            isLoading = uiState.isSubmitting,
            modifier = Modifier.fillMaxWidth()
        )

        if (uiState.errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.errorMessage ?: "",
                style = GlobalIetaTheme.typography.small.copy(color = GlobalIETAColor.Error)
            )
        }
    }

    // Success Dialog
    if (uiState.isSubmitted) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissSuccessDialog() },
            containerColor = GlobalIETAColor.DeepSurface,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = GlobalIETAColor.Success,
                    modifier = Modifier.size(36.dp)
                )
            },
            title = {
                Text(
                    text = "REQUEST SUBMITTED SUCCESSFULLY",
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        color = Color.White
                    )
                )
            },
            text = {
                Text(
                    text = "Reference ID: ${uiState.submittedReferenceId}\nYour request has been routed to the Global IETA Allocation Core. Our team will contact you within 24 operational hours.",
                    style = GlobalIetaTheme.typography.body.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            },
            confirmButton = {
                GlobalIetaButton(
                    text = "ACKNOWLEDGE",
                    onClick = { viewModel.dismissSuccessDialog() }
                )
            }
        )
    }
}
