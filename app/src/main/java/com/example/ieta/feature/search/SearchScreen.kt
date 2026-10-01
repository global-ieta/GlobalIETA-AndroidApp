package com.example.ieta.feature.search

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
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaTextField
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class SearchResultNode(
    val title: String,
    val category: String,
    val route: String,
    val description: String
)

val searchNodesIndex = listOf(
    SearchResultNode("AURA Quantum AI Core", "AURA Platform", "aura", "Context-aware AI operator and neural assistant."),
    SearchResultNode("ARIN Spatial Engine", "Spatial Engine", "arin", "6DoF pose alignment and 3D neural mesh."),
    SearchResultNode("AERO Drone Telemetry", "Mobility", "aero", "Airspace orchestration and live 3D radar."),
    SearchResultNode("IETA Campus Operations", "EduTech", "campus", "School workspace for students, faculty, and exams."),
    SearchResultNode("IETA Connector Comms", "Business", "connector", "Institutional communications and broadcast channels."),
    SearchResultNode("RideOS Equine Fleet", "Equine", "rideos", "Equestrian management and gait telemetry."),
    SearchResultNode("IETA Workspace Suite", "Enterprise", "workspace", "Spatial office suite, tasks, and document editor."),
    SearchResultNode("Global Asset Marketplace", "Trade Hub", "marketplace", "DeCentralized trading floor for 3D assets & gear.")
)

@Composable
fun SearchScreen(
    onNavigateToRoute: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    val filteredResults = searchNodesIndex.filter {
        query.isBlank() ||
                it.title.contains(query, ignoreCase = true) ||
                it.category.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
    }

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
                text = "GLOBAL SEARCH PROTOCOL",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "SEARCH SPATIAL MATRIX",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalIetaTextField(
            value = query,
            onValueChange = { query = it },
            placeholder = "Search products, tools, docs, or verticals...",
            leadingIcon = Icons.Rounded.Search
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "[ INDEXED NODES (${filteredResults.size}) ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                color = GlobalIETAColor.PrimaryCyan
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        filteredResults.forEach { node ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                    .clickable { onNavigateToRoute(node.route) }
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = node.category.uppercase(),
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 9.sp,
                                color = GlobalIETAColor.PrimaryCyan
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = node.title,
                            style = GlobalIetaTheme.typography.cardTitle.copy(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = node.description,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.SecondaryText
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = "Navigate",
                        tint = GlobalIETAColor.MutedText,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}
