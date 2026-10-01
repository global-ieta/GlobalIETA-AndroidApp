package com.example.ieta.feature.billing

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
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.Star
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

data class PricingPlan(
    val title: String,
    val price: String,
    val billingCycle: String,
    val features: List<String>,
    val isCurrent: Boolean,
    val color: Color
)

val plansList = listOf(
    PricingPlan(
        title = "ACADEMY & SCHOOL",
        price = "$499",
        billingCycle = "/ month",
        features = listOf("Up to 500 Students", "Campus & Connector Core", "Standard AURA AI Support"),
        isCurrent = false,
        color = GlobalIETAColor.EducationBlue
    ),
    PricingPlan(
        title = "ENTERPRISE CORE PLATINUM",
        price = "$2,499",
        billingCycle = "/ month",
        features = listOf("Unlimited Spatial Nodes", "Full AURA + ARIN + RideOS Mesh", "Dedicated SLA & 24/7 Support"),
        isCurrent = true,
        color = GlobalIETAColor.LegalGold
    )
)

data class InvoiceItem(
    val id: String,
    val date: String,
    val amount: String,
    val status: String
)

val mockInvoices = listOf(
    InvoiceItem("INV-2026-088", "Oct 01, 2026", "$2,499.00", "PAID"),
    InvoiceItem("INV-2026-042", "Sep 01, 2026", "$2,499.00", "PAID"),
    InvoiceItem("INV-2026-011", "Aug 01, 2026", "$2,499.00", "PAID")
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BillingScreen(
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
                .border(1.dp, GlobalIETAColor.LegalGold, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(GlobalIETAColor.LegalGold)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "METERED SUBSCRIPTIONS & LICENSING",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.LegalGold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "BILLING & SUBSCRIPTION MATRIX",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Manage enterprise software licenses, node entitlements, active payment methods, and historical billing invoices.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Active Subscription Card
        GlobalIetaCard(
            technicalLabel = "[ ACTIVE TIER // PLATINUM ]",
            accentColor = GlobalIETAColor.LegalGold
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "GLOBAL CORE PLATINUM",
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Renewal Date: November 01, 2026",
                        style = GlobalIetaTheme.typography.small.copy(
                            color = GlobalIETAColor.SecondaryText
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(GlobalIETAColor.Success.copy(alpha = 0.2f))
                        .border(1.dp, GlobalIETAColor.Success, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "ACTIVE",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontSize = 9.sp,
                            color = GlobalIETAColor.Success
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ ENTERPRISE LICENSING PLANS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.LegalGold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Plans Grid
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            plansList.forEach { plan ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(
                            1.dp,
                            if (plan.isCurrent) plan.color else GlobalIETAColor.Border,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            text = plan.title,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = plan.color
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = plan.price,
                                style = GlobalIetaTheme.typography.hero.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = plan.billingCycle,
                                style = GlobalIetaTheme.typography.small.copy(
                                    fontSize = 10.sp,
                                    color = GlobalIETAColor.MutedText
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        plan.features.forEach { feat ->
                            Text(
                                text = "• $feat",
                                style = GlobalIetaTheme.typography.small.copy(
                                    fontSize = 10.sp,
                                    color = GlobalIETAColor.SecondaryText
                                ),
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "[ INVOICE HISTORY ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.LegalGold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        mockInvoices.forEach { inv ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.ReceiptLong,
                            contentDescription = null,
                            tint = GlobalIETAColor.LegalGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = inv.id,
                                style = GlobalIetaTheme.typography.cardTitle.copy(
                                    fontSize = 13.sp,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = inv.date,
                                style = GlobalIetaTheme.typography.small.copy(
                                    color = GlobalIETAColor.MutedText
                                )
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = inv.amount,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = inv.status,
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = GlobalIETAColor.Success
                            )
                        )
                    }
                }
            }
        }
    }
}
