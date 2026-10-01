package com.example.ieta.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.components.GlobalIetaCard
import com.example.ieta.core.components.GlobalIetaLogo
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun AboutScreen(
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
                text = "SYSTEM ARCHITECTURE & LEGAL ENTITY",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.PrimaryCyan
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        GlobalIetaLogo()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SYSTEM SPECS & CORPORATE ARCHITECTURE",
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mission & Technology
        GlobalIetaCard(
            technicalLabel = "[ CORE PLATFORM ENGINE ]",
            accentColor = GlobalIETAColor.PrimaryCyan
        ) {
            Text(
                text = "Global IETA Quantum Infrastructure",
                style = GlobalIetaTheme.typography.cardTitle.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Built on sub-millisecond gRPC channels, Jetpack Compose 3 declarative UI layers, and AURA ambient intelligence core. Designed for edge-to-edge spatial operations.",
                style = GlobalIetaTheme.typography.body.copy(color = GlobalIETAColor.SecondaryText)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Official Corporate Entity Card
        GlobalIetaCard(
            technicalLabel = "[ CORPORATE REGISTRATION & CREDENTIALS ]",
            accentColor = GlobalIETAColor.LegalGold
        ) {
            Text(
                text = "GLOBALIETA EDUCATE TECHNOLOGY PRIVATE LIMITED",
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Official Corporate Entity: GLOBALIETA EDUCATE TECHNOLOGY PRIVATE LIMITED\nDomain: global-ieta.io\nSecurity Standard: ISO 27001 & Zero-Trust Data Guardrails\nOperational Matrix: Global Connected Spatial Nodes",
                style = GlobalIetaTheme.typography.body.copy(
                    fontSize = 12.sp,
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }
    }
}
