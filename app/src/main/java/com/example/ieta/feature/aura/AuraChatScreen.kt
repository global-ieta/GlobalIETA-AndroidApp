package com.example.ieta.feature.aura

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaTextField
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaMotion
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.cyanGlow
import com.example.ieta.domain.model.AuraMessage
import com.example.ieta.domain.model.MessageSender

/**
 * Interactive AURA Chat screen / panel displaying the stream of quantum neural messages,
 * minimal technical header ([ AURA // SHARED INTELLIGENCE MESH ]),
 * glowing cyan user bubbles, subtle dark response bubbles, and smooth message entrance animations.
 */
@Composable
fun AuraChatScreen(
    messages: List<AuraMessage>,
    inputText: String,
    isProcessing: Boolean,
    onInputTextChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    onPromptClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBackground)
            .padding(12.dp)
    ) {
        // Minimal Technical Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "[ AURA // SHARED INTELLIGENCE MESH ]",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 11.sp,
                    letterSpacing = 1.2.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )

            Text(
                text = "QUANTUM MESH: ONLINE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 9.sp,
                    color = GlobalIETAColor.Success
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Message List with smooth entrance animation
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = messages,
                key = { it.id }
            ) { msg ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = GlobalIetaMotion.smoothTweenSpec()) +
                            slideInVertically(
                                animationSpec = GlobalIetaMotion.smoothTweenSpec(),
                                initialOffsetY = { fullHeight -> fullHeight / 2 }
                            )
                ) {
                    AuraMessageBubble(
                        msg = msg,
                        onPromptClick = onPromptClick
                    )
                }
            }

            if (isProcessing) {
                item(key = "aura-processing-indicator") {
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(animationSpec = GlobalIetaMotion.quickTweenSpec()) +
                                slideInVertically(
                                    animationSpec = GlobalIetaMotion.quickTweenSpec(),
                                    initialOffsetY = { fullHeight -> fullHeight / 3 }
                                )
                    ) {
                        AuraProcessingIndicator()
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        AuraInputBar(
            inputText = inputText,
            isProcessing = isProcessing,
            onInputTextChange = onInputTextChange,
            onSendMessage = onSendMessage
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuraMessageBubble(
    msg: AuraMessage,
    onPromptClick: (String) -> Unit
) {
    val isUser = msg.sender == MessageSender.USER

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Icon(
                imageVector = if (isUser) Icons.Rounded.Person else Icons.Rounded.AutoAwesome,
                contentDescription = null,
                tint = if (isUser) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.AuraBlue,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isUser) "OPERATOR // USER" else "AURA CORE",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 9.sp,
                    color = if (isUser) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.AuraBlue
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .then(
                    if (isUser) {
                        // Glowing cyan user message bubble
                        Modifier
                            .cyanGlow(
                                color = GlobalIETAColor.PrimaryCyan,
                                blurRadius = 10.dp,
                                spread = 1.dp,
                                alpha = 0.35f,
                                cornerRadius = 14.dp
                            )
                            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 4.dp, bottomStart = 14.dp, bottomEnd = 14.dp))
                            .background(GlobalIETAColor.ElevatedSurface)
                            .border(1.dp, GlobalIETAColor.PrimaryCyan, RoundedCornerShape(topStart = 14.dp, topEnd = 4.dp, bottomStart = 14.dp, bottomEnd = 14.dp))
                    } else {
                        // Subtle dark surface response bubble
                        Modifier
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 14.dp, bottomStart = 14.dp, bottomEnd = 14.dp))
                            .background(GlobalIETAColor.DeepSurface)
                            .border(1.dp, GlobalIETAColor.AuraBlue.copy(alpha = 0.4f), RoundedCornerShape(topStart = 4.dp, topEnd = 14.dp, bottomStart = 14.dp, bottomEnd = 14.dp))
                    }
                )
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = msg.text,
                    style = GlobalIetaTheme.typography.body.copy(
                        fontSize = 13.sp,
                        color = GlobalIETAColor.PrimaryText
                    )
                )

                if (!isUser && msg.suggestedActions.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        msg.suggestedActions.forEach { action ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(GlobalIETAColor.ElevatedSurface)
                                    .border(0.8.dp, GlobalIETAColor.AuraBlue.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                    .clickable { onPromptClick(action) }
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = action,
                                    style = GlobalIetaTheme.typography.small.copy(
                                        fontSize = 10.sp,
                                        color = GlobalIETAColor.AuraBlue
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AuraProcessingIndicator() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(GlobalIETAColor.DeepSurface)
            .border(1.dp, GlobalIETAColor.AuraBlue.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(14.dp),
            color = GlobalIETAColor.PrimaryCyan,
            strokeWidth = 1.5.dp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "AURA IS ANALYZING QUANTUM CONTEXT...",
            style = GlobalIetaTheme.typography.technicalLabel.copy(
                fontSize = 10.sp,
                color = GlobalIETAColor.PrimaryCyan
            )
        )
    }
}

@Composable
fun AuraInputBar(
    inputText: String,
    isProcessing: Boolean,
    onInputTextChange: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GlobalIetaTextField(
            value = inputText,
            onValueChange = onInputTextChange,
            placeholder = "Ask AURA anything about Global IETA...",
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { /* Voice input mock */ },
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(8.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Mic,
                contentDescription = "Voice Input",
                tint = GlobalIETAColor.AuraBlue
            )
        }

        IconButton(
            onClick = onSendMessage,
            enabled = inputText.isNotBlank() && !isProcessing,
            modifier = Modifier
                .size(44.dp)
                .then(
                    if (inputText.isNotBlank()) {
                        Modifier.cyanGlow(
                            color = GlobalIETAColor.PrimaryCyan,
                            blurRadius = 8.dp,
                            alpha = 0.45f
                        )
                    } else Modifier
                )
                .clip(RoundedCornerShape(8.dp))
                .background(if (inputText.isNotBlank()) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.ElevatedSurface)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "Send Message",
                tint = if (inputText.isNotBlank()) GlobalIETAColor.PrimaryBackground else GlobalIETAColor.MutedText
            )
        }
    }
}
