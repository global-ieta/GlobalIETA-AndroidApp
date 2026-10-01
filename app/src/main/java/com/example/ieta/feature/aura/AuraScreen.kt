package com.example.ieta.feature.aura

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Psychology
import androidx.compose.material.icons.rounded.RecordVoiceOver
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaMotion
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.auraPulseGlow

@Composable
fun AuraScreen(
    modifier: Modifier = Modifier,
    viewModel: AuraViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Soft radial cyan light background emanating from calm intelligence core onto dark #020812 surface
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBackground)
            .drawBehind {
                drawRect(
                    brush = Brush.radialGradient(
                        colorStops = arrayOf(
                            0.0f to GlobalIETAColor.PrimaryCyan.copy(alpha = 0.15f),
                            0.35f to GlobalIETAColor.AuraBlue.copy(alpha = 0.08f),
                            0.75f to Color(0xFF020812),
                            1.0f to Color(0xFF020812)
                        ),
                        center = Offset(size.width * 0.5f, size.height * 0.25f),
                        radius = maxOf(size.width, size.height) * 0.65f
                    )
                )
            }
    ) {
        val isWideScreen = maxWidth >= 600.dp

        if (isWideScreen) {
            // Dual Pane Layout (Tablet / Desktop)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Pane: Intelligence Core, Header, Features & Suggested Prompts
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(end = 8.dp)
                ) {
                    AuraHeroHeader()
                    Spacer(modifier = Modifier.height(16.dp))
                    AuraIntelligenceCore(isProcessing = uiState.isProcessing)
                    Spacer(modifier = Modifier.height(16.dp))
                    AuraFeatureCards()
                    Spacer(modifier = Modifier.height(16.dp))
                    AuraSuggestedPrompts(
                        prompts = uiState.quickPrompts,
                        onPromptClick = { viewModel.sendMessage(it) }
                    )
                }

                // Right Pane: Interactive Chat Panel with minimal technical header & smooth animations
                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.AuraBlue.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                ) {
                    AuraChatScreen(
                        messages = uiState.messages,
                        inputText = uiState.inputText,
                        isProcessing = uiState.isProcessing,
                        onInputTextChange = { viewModel.onInputTextChange(it) },
                        onSendMessage = { viewModel.sendMessage() },
                        onPromptClick = { viewModel.sendMessage(it) }
                    )
                }
            }
        } else {
            // Single Pane Layout (Phone)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        AuraHeroHeader()
                    }
                    item {
                        AuraIntelligenceCore(isProcessing = uiState.isProcessing)
                    }
                    item {
                        AuraFeatureCards()
                    }
                    item {
                        AuraSuggestedPrompts(
                            prompts = uiState.quickPrompts,
                            onPromptClick = { viewModel.sendMessage(it) }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "[ AURA // SHARED INTELLIGENCE MESH ]",
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.2.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GlobalIETAColor.PrimaryCyan
                            )
                        )
                    }
                    items(
                        items = uiState.messages,
                        key = { it.id }
                    ) { msg ->
                        AuraMessageBubble(
                            msg = msg,
                            onPromptClick = { viewModel.sendMessage(it) }
                        )
                    }
                    if (uiState.isProcessing) {
                        item {
                            AuraProcessingIndicator()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                AuraInputBar(
                    inputText = uiState.inputText,
                    isProcessing = uiState.isProcessing,
                    onInputTextChange = { viewModel.onInputTextChange(it) },
                    onSendMessage = { viewModel.sendMessage() }
                )
            }
        }
    }
}

@Composable
private fun AuraIntelligenceCore(
    modifier: Modifier = Modifier,
    isProcessing: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "AuraCoreBreathing")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = GlobalIetaMotion.AuraBreathing, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CoreScale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = GlobalIetaMotion.AuraBreathing, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CoreAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, GlobalIETAColor.PrimaryCyan.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            GlobalIETAColor.PrimaryCyan.copy(alpha = pulseAlpha * 0.30f),
                            GlobalIETAColor.AuraBlue.copy(alpha = pulseAlpha * 0.12f),
                            Color.Transparent
                        ),
                        center = center,
                        radius = size.minDimension * 0.85f * pulseScale
                    ),
                    center = center,
                    radius = size.minDimension * 0.85f * pulseScale
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .graphicsLayer {
                        scaleX = pulseScale
                        scaleY = pulseScale
                    }
                    .auraPulseGlow(
                        color = GlobalIETAColor.PrimaryCyan,
                        radius = 20.dp,
                        alpha = pulseAlpha
                    )
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                GlobalIETAColor.PrimaryCyan,
                                GlobalIETAColor.AuraBlue,
                                GlobalIETAColor.SecondaryBackground
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = "AURA Core",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isProcessing) "AURA CORE // SYNAPSE PROCESSING..." else "CALM INTELLIGENCE CORE // ACTIVE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    letterSpacing = 1.2.sp,
                    color = if (isProcessing) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.AuraBlue
                )
            )
        }
    }
}

@Composable
private fun AuraHeroHeader() {
    Column {
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
                text = "QUANTUM NEURAL OPERATOR",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.AuraBlue
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "AURA / INTELLIGENCE WITH A HUMAN PRESENCE.",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "AURA is Global IETA's ambient intelligence core, providing human-aligned contextual awareness, proactive guidance, and seamless multi-screen action across all verticals.",
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.SecondaryText
            )
        )
    }
}

@Composable
private fun AuraFeatureCards() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AuraFeatureItem(
            title = "Understands Intent",
            description = "Analyzes high-level organizational goals and spatial context rather than parsing simple keyword commands.",
            icon = Icons.Rounded.Psychology,
            color = GlobalIETAColor.PrimaryCyan
        )
        AuraFeatureItem(
            title = "Preserves Context",
            description = "Maintains persistent cross-session memory across Campus, RideOS, Workspace, and Marketplace nodes.",
            icon = Icons.Rounded.RecordVoiceOver,
            color = GlobalIETAColor.AuraBlue
        )
        AuraFeatureItem(
            title = "Guides Never Overrides",
            description = "Presents deterministic recommendations with transparent confidence metrics while keeping humans in control.",
            icon = Icons.Rounded.Shield,
            color = GlobalIETAColor.ElectricBlue
        )
    }
}

@Composable
private fun AuraFeatureItem(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = GlobalIetaTheme.typography.cardTitle.copy(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = GlobalIetaTheme.typography.small.copy(
                        color = GlobalIETAColor.SecondaryText
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AuraSuggestedPrompts(
    prompts: List<String>,
    onPromptClick: (String) -> Unit
) {
    Column {
        Text(
            text = "[ SUGGESTED COMMAND PROMPTS ]",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                fontSize = 10.sp,
                color = GlobalIETAColor.PrimaryCyan
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val allPrompts = prompts + listOf(
                "What can Global IETA do for my school?",
                "I run a riding school."
            )
            allPrompts.distinct().forEach { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(GlobalIETAColor.ElevatedSurface)
                        .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(16.dp))
                        .clickable { onPromptClick(prompt) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = GlobalIETAColor.PrimaryCyan,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = prompt,
                            style = GlobalIetaTheme.typography.small.copy(
                                color = GlobalIETAColor.PrimaryText
                            )
                        )
                    }
                }
            }
        }
    }
}
