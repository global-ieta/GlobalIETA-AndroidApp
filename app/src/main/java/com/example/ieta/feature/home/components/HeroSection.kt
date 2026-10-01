package com.example.ieta.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaButton
import com.example.ieta.core.components.GlobalIetaOutlinedButton
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun HeroSection(
    onExploreClick: () -> Unit,
    onMeetAuraClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // Eyebrow badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, GlobalIETAColor.PrimaryCyan.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
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
                text = "[ GLOBAL OPERATIONAL MESH // ONLINE ]",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Headline
        Text(
            text = "AI AND SOFTWARE /\nFOR EVERYDAY /\nOPERATIONS.",
            style = GlobalIetaTheme.typography.display.copy(
                fontSize = 36.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4F7FA)
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Supporting text
        Text(
            text = "Global IETA builds AI, communication and operations software for businesses, schools and service organisations.",
            style = GlobalIetaTheme.typography.body.copy(
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = GlobalIETAColor.SecondaryText
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action CTAs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GlobalIetaButton(
                text = "EXPLORE ECOSYSTEM",
                onClick = onExploreClick,
                modifier = Modifier.weight(1f)
            )

            GlobalIetaOutlinedButton(
                text = "MEET AURA",
                onClick = onMeetAuraClick,
                borderColor = GlobalIETAColor.AuraBlue,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
