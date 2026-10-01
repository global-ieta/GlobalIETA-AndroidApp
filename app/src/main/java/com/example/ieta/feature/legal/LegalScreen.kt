package com.example.ieta.feature.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun PrivacyScreen(
    modifier: Modifier = Modifier
) {
    LegalDocumentLayout(
        title = "PRIVACY POLICY & DATA GUARDRAILS",
        tag = "GOV-PRIV-01",
        content = """
            Global IETA operates under a strict Zero-Trust Data Architecture.
            
            1. DATA COLLECTION & MINIMIZATION
            We only process telemetry and credentials necessary for spatial pose alignment, AURA neural context preservation, and institutional communication.
            
            2. CHILDREN'S DATA & ARIN PROTECTION
            All ARIN early learning modules comply with COPPA, FERPA, and international child data protection standards. Biometric telemetry from children is never stored on persistent servers.
            
            3. ENCRYPTION AT REST & IN TRANSIT
            All CONNECTOR REST/gRPC API channels utilize TLS 1.3 encryption with 256-bit AES spatial key derivation.
        """.trimIndent(),
        modifier = modifier
    )
}

@Composable
fun TermsScreen(
    modifier: Modifier = Modifier
) {
    LegalDocumentLayout(
        title = "TERMS OF SERVICE & LICENSING",
        tag = "GOV-TRM-02",
        content = """
            1. ENTERPRISE SOFTWARE LICENSE
            Global IETA grants organizational users a non-exclusive, non-transferable license to access spatial nodes, AURA AI operators, and platform verticals according to their active subscription tier.
            
            2. SYSTEM SLA & UPTIME GUARANTEE
            Platinum tier subscribers receive a guaranteed 99.999% mesh operational uptime.
            
            3. INTELLECTUAL PROPERTY
            All 3D assets, neural models, and spatial mesh code remain the property of GLOBALIETA EDUCATE TECHNOLOGY PRIVATE LIMITED or its licensors.
        """.trimIndent(),
        modifier = modifier
    )
}

@Composable
private fun LegalDocumentLayout(
    title: String,
    tag: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalIETAColor.PrimaryBg)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
                text = "PLATFORM GOVERNANCE & LEGAL",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    fontSize = 10.sp,
                    color = GlobalIETAColor.LegalGold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = title,
            style = GlobalIetaTheme.typography.hero.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalIetaCard(
            technicalLabel = "[ $tag ]",
            accentColor = GlobalIETAColor.LegalGold
        ) {
            Text(
                text = content,
                style = GlobalIetaTheme.typography.body.copy(
                    lineHeight = 22.sp,
                    color = GlobalIETAColor.SecondaryText
                )
            )
        }
    }
}
