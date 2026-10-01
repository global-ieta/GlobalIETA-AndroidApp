package com.example.ieta.feature.marketplace

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.components.GlobalIetaOutlinedButton
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class TradeCategory(
    val id: String,
    val name: String,
    val count: String,
    val icon: ImageVector
)

val tradeCategories = listOf(
    TradeCategory("horses", "Horses", "142 Active", Icons.Rounded.Pets),
    TradeCategory("studs", "Studs", "38 Breeding", Icons.Rounded.Pets),
    TradeCategory("shop", "Shop & Gear", "1,200 Items", Icons.Rounded.ShoppingBag),
    TradeCategory("services", "Services", "85 Vets & Trainers", Icons.Rounded.Star),
    TradeCategory("transport", "Transport", "24 Transporters", Icons.Rounded.DirectionsBus),
    TradeCategory("jobs", "Jobs", "18 Openings", Icons.Rounded.Work),
    TradeCategory("events", "Events", "12 Shows", Icons.Rounded.Event)
)

data class MarketplaceItem(
    val id: String,
    val title: String,
    val category: String,
    val price: String,
    val location: String,
    val rating: Double,
    val tagline: String
)

val mockMarketplaceItems = listOf(
    MarketplaceItem("MKT-001", "Warmblood Showjumper Digital Twin", "Horses", "$85,000", "Kentucky, USA", 4.9, "Grade A jumper with verified RideOS gait telemetry."),
    MarketplaceItem("MKT-002", "Pro-Rider Anatomic Saddle", "Shop & Gear", "$2,450", "Wellington, FL", 4.8, "Custom titanium tree saddle with biomechanical sensor pockets."),
    MarketplaceItem("MKT-003", "Equine Express Transporter Shuttle", "Transport", "$1,200 / trip", "Ocala, FL", 5.0, "Climate-controlled 6-horse trailer with live AURA video feed."),
    MarketplaceItem("MKT-004", "Head Dressage Instructor Opening", "Jobs", "$75k / year", "Aachen, Germany", 4.95, "Seeking certified FEi instructor for international academy.")
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MarketplaceScreen(
    modifier: Modifier = Modifier
) {
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    var showFilterSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GlobalIETAColor.DeepSurface)
                            .border(1.dp, GlobalIETAColor.MarketplaceOrange, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(GlobalIETAColor.MarketplaceOrange)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "EQUESTRIAN TRADE HUB",
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 10.sp,
                                color = GlobalIETAColor.MarketplaceOrange
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "MARKETPLACE / EQUESTRIAN EXCHANGE",
                        style = GlobalIetaTheme.typography.hero.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                IconButton(
                    onClick = { showFilterSheet = true },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.MarketplaceOrange, RoundedCornerShape(8.dp))
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FilterList,
                        contentDescription = "Filter",
                        tint = GlobalIETAColor.MarketplaceOrange
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Category Chips Row
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                tradeCategories.forEachIndexed { index, cat ->
                    val isSelected = index == selectedCategoryIndex

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) GlobalIETAColor.MarketplaceOrange else GlobalIETAColor.DeepSurface)
                            .border(
                                1.dp,
                                if (isSelected) GlobalIETAColor.MarketplaceOrange else GlobalIETAColor.Border,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedCategoryIndex = index }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = cat.icon,
                                contentDescription = null,
                                tint = if (isSelected) GlobalIETAColor.PrimaryBg else GlobalIETAColor.MarketplaceOrange,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = cat.name,
                                style = GlobalIetaTheme.typography.small.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) GlobalIETAColor.PrimaryBg else Color.White
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isWideScreen) {
                // Adaptive 2-Column Grid on Wide Screen
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(mockMarketplaceItems) { item ->
                        MarketplaceItemCard(item = item)
                    }
                }
            } else {
                // Single Column on Phone
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    mockMarketplaceItems.forEach { item ->
                        MarketplaceItemCard(item = item)
                    }
                }
            }
        }

        // Filter Bottom Sheet
        if (showFilterSheet) {
            ModalBottomSheet(
                onDismissRequest = { showFilterSheet = false },
                sheetState = sheetState,
                containerColor = GlobalIETAColor.DeepSurface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "[ MARKETPLACE FILTER MATRIX ]",
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            color = GlobalIETAColor.MarketplaceOrange
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Filter Trade Listings",
                        style = GlobalIetaTheme.typography.sectionTitle.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Price Range: All Tiers ($0 - $100,000+)",
                        style = GlobalIetaTheme.typography.body.copy(color = GlobalIETAColor.SecondaryText)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Location: Global Operational Mesh",
                        style = GlobalIetaTheme.typography.body.copy(color = GlobalIETAColor.SecondaryText)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Verification: RideOS Gait Telemetry Verified",
                        style = GlobalIetaTheme.typography.body.copy(color = GlobalIETAColor.Success)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    GlobalIetaButton(
                        text = "APPLY FILTERS",
                        onClick = { showFilterSheet = false },
                        accentColor = GlobalIETAColor.MarketplaceOrange,
                        glowColor = GlobalIETAColor.MarketplaceOrange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun MarketplaceItemCard(item: MarketplaceItem) {
    GlobalIetaCard(
        technicalLabel = "[ ${item.category.uppercase()} // ${item.id} ]",
        accentColor = GlobalIETAColor.MarketplaceOrange
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.price,
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = GlobalIETAColor.MarketplaceOrange
                )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = GlobalIETAColor.Warning,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${item.rating} • ${item.location}",
                style = GlobalIetaTheme.typography.small.copy(
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.tagline,
            style = GlobalIetaTheme.typography.body.copy(
                fontSize = 12.sp,
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        GlobalIetaOutlinedButton(
            text = "VIEW LISTING & TELEMETRY",
            onClick = { /* Inspect item */ },
            borderColor = GlobalIETAColor.MarketplaceOrange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
