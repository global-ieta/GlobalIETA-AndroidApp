package com.example.ieta.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaMotion
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.auraPulseGlow
import com.example.ieta.core.navigation.Screen

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

val defaultBottomNavItems = listOf(
    BottomNavItem(Screen.Home.route, "Home", Icons.Rounded.Home),
    BottomNavItem(Screen.Industries.route, "Verticals", Icons.Rounded.Category),
    BottomNavItem(Screen.Marketplace.route, "Market", Icons.Rounded.Storefront),
    BottomNavItem(Screen.Profile.route, "Profile", Icons.Rounded.Person)
)

@Composable
fun GlobalIetaBottomBar(
    currentRoute: String = Screen.Home.route,
    onNavigate: (String) -> Unit = {},
    onAuraClick: () -> Unit = {}
) {
    // Slow breathing animation scale 0.98 -> 1.02 over 2600ms easing curve while idle
    val infiniteTransition = rememberInfiniteTransition(label = "AuraBreathingTransition")
    val auraScale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = GlobalIetaMotion.AuraBreathing, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AuraScale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = GlobalIetaMotion.AuraBreathing, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AuraPulseAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Main Navigation bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(GlobalIETAColor.SecondaryBg)
                .drawBehind {
                    drawLine(
                        color = GlobalIETAColor.MutedBorder,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            defaultBottomNavItems.take(2).forEach { item ->
                val selected = currentRoute == item.route
                BottomNavItemCell(
                    item = item,
                    isSelected = selected,
                    onClick = { onNavigate(item.route) }
                )
            }

            // Spacer for floating AURA button in center
            Box(modifier = Modifier.size(56.dp))

            defaultBottomNavItems.drop(2).take(2).forEach { item ->
                val selected = currentRoute == item.route
                BottomNavItemCell(
                    item = item,
                    isSelected = selected,
                    onClick = { onNavigate(item.route) }
                )
            }
        }

        // Floating Center Glowing AURA Action Button
        Box(
            modifier = Modifier
                .offset(y = (-18).dp)
                .graphicsLayer {
                    scaleX = auraScale
                    scaleY = auraScale
                }
                .auraPulseGlow(color = GlobalIETAColor.PrimaryCyan, radius = 14.dp, alpha = pulseAlpha)
                .size(58.dp)
                .clip(CircleShape)
                .background(GlobalIETAColor.DeepSurface)
                .border(2.dp, GlobalIETAColor.PrimaryCyan, CircleShape)
                .clickable { onAuraClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.AutoAwesome,
                contentDescription = "AURA AI",
                tint = GlobalIETAColor.PrimaryCyan,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
private fun BottomNavItemCell(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedIconScale by animateFloatAsState(
        targetValue = if (isSelected) 1.15f else 0.95f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "IconScale"
    )

    val animatedIndicatorWidth by animateDpAsState(
        targetValue = if (isSelected) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "IndicatorWidth"
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.SecondaryText,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "TextColor"
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Animated Top Indicator Line with Glow Accent
        Box(
            modifier = Modifier
                .height(3.dp)
                .width(animatedIndicatorWidth)
                .clip(RoundedCornerShape(1.5.dp))
                .background(GlobalIETAColor.PrimaryCyan)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = animatedTextColor,
            modifier = Modifier
                .size(22.dp)
                .graphicsLayer {
                    scaleX = animatedIconScale
                    scaleY = animatedIconScale
                }
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = item.title,
            style = GlobalIetaTheme.typography.small.copy(
                color = animatedTextColor
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaBottomBarPreview() {
    GlobalIetaTheme {
        GlobalIetaBottomBar()
    }
}
