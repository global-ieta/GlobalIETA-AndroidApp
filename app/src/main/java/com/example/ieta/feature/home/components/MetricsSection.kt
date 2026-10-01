package com.example.ieta.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

data class MetricData(
    val label: String,
    val value: String,
)

val defaultMetrics = listOf(
    MetricData("ACTIVE NODES", "1,845,000+"),
    MetricData("ACTIVE COUNTRIES", "142"),
    MetricData("SYSTEM UPTIME", "99.999%")
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MetricsSection(
    modifier: Modifier = Modifier,
    metrics: List<MetricData> = defaultMetrics
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        metrics.forEach { metric ->
            Box(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .clip(RoundedCornerShape(12.dp))
                    .background(GlobalIETAColor.DeepSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Column {
                    Text(
                        text = metric.label,
                        style = GlobalIetaTheme.typography.technicalLabel.copy(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GlobalIETAColor.PrimaryCyan
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = metric.value,
                        style = GlobalIetaTheme.typography.cardTitle.copy(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = GlobalIETAColor.PrimaryCyan
                        )
                    )
                }
            }
        }
    }
}
