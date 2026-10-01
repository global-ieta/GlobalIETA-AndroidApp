package com.example.ieta.core.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun GlobalIetaTopBar(
    title: String? = null,
    canNavigateBack: Boolean = false,
    onNavigateBack: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    notificationCount: Int = 2
) {
    val infiniteTransition = rememberInfiniteTransition(label = "OperationalStatus")
    val statusAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "DotAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GlobalIETAColor.PrimaryBg,
                        GlobalIETAColor.SecondaryBg
                    )
                )
            )
            .drawBehind {
                // 1dp #102536 bottom separator line
                drawLine(
                    color = GlobalIETAColor.MutedBorder,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (canNavigateBack) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = GlobalIETAColor.PrimaryCyan
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            color = GlobalIETAColor.PrimaryText
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(GlobalIETAColor.Success.copy(alpha = statusAlpha))
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "● OPERATIONAL",
                            style = GlobalIetaTheme.typography.technicalLabel.copy(
                                color = GlobalIETAColor.Success,
                                fontSize = 10.sp
                            )
                        )
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        GlobalIetaLogo(
                            showTagline = false
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(GlobalIETAColor.Success.copy(alpha = statusAlpha))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "OPERATIONAL",
                                style = GlobalIetaTheme.typography.technicalLabel.copy(
                                    color = GlobalIETAColor.Success,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }

            // Un-crowded action icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search",
                        tint = GlobalIETAColor.SecondaryText
                    )
                }

                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    BadgedBox(
                        badge = {
                            if (notificationCount > 0) {
                                Badge(
                                    containerColor = GlobalIETAColor.PrimaryCyan,
                                    contentColor = GlobalIETAColor.PrimaryBg
                                ) {
                                    Text(
                                        text = if (notificationCount > 9) "9+" else notificationCount.toString(),
                                        style = GlobalIetaTheme.typography.small
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Notifications,
                            contentDescription = "Notifications",
                            tint = GlobalIETAColor.SecondaryText
                        )
                    }
                }

                IconButton(
                    onClick = onMenuClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu",
                        tint = GlobalIETAColor.PrimaryCyan
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GlobalIetaTopBarPreview() {
    GlobalIetaTheme {
        GlobalIetaTopBar()
    }
}
