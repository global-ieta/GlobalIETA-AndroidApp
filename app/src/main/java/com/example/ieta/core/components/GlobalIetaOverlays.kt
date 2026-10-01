package com.example.ieta.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalIetaBottomSheet(
    onDismissRequest: () -> Unit,
    title: String? = null,
    technicalLabel: String = "[ SYSTEM PANEL ]",
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable () -> Unit
) {
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isContentVisible = true
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = GlobalIETAColor.DeepSurface,
        scrimColor = GlobalIETAColor.PrimaryBg.copy(alpha = 0.82f),
        dragHandle = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(GlobalIETAColor.PrimaryCyan)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    GlobalIETAColor.Border,
                    RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AnimatedVisibility(
                    visible = isContentVisible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 })
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = technicalLabel,
                                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                                        color = GlobalIETAColor.PrimaryCyan
                                    )
                                )
                                if (title != null) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = title,
                                        style = GlobalIetaTheme.typography.cardTitle.copy(
                                            color = GlobalIETAColor.PrimaryText
                                        )
                                    )
                                }
                            }
                            IconButton(onClick = onDismissRequest) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "Close",
                                    tint = GlobalIETAColor.SecondaryText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        content()
                    }
                }
            }
        }
    }
}

@Composable
fun GlobalIetaDialog(
    onDismissRequest: () -> Unit,
    title: String,
    technicalLabel: String = "[ DIALOG // CONFIRMATION ]",
    accentColor: Color = GlobalIETAColor.PrimaryCyan,
    content: @Composable () -> Unit
) {
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isContentVisible = true
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(
                visible = isContentVisible,
                enter = fadeIn() + scaleIn(initialScale = 0.92f) + slideInVertically(initialOffsetY = { it / 4 })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(GlobalIETAColor.DeepSurface)
                        .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(20.dp))
                        .padding(20.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = technicalLabel,
                                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                                        color = accentColor
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = title,
                                    style = GlobalIetaTheme.typography.cardTitle.copy(
                                        color = GlobalIETAColor.PrimaryText
                                    )
                                )
                            }
                            IconButton(onClick = onDismissRequest) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "Close",
                                    tint = GlobalIETAColor.SecondaryText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        content()
                    }
                }
            }
        }
    }
}
