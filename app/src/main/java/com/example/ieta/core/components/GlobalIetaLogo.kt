package com.example.ieta.core.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme

@Composable
fun GlobalIetaLogoIcon(
    modifier: Modifier = Modifier,
    size: Dp = 32.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val cx = w / 2f
        val cy = h / 2f
        val r = w / 2f - 2.dp.toPx()

        // Outer Hexagon
        val path = Path().apply {
            moveTo(cx, cy - r)
            lineTo(cx + r * 0.866f, cy - r * 0.5f)
            lineTo(cx + r * 0.866f, cy + r * 0.5f)
            lineTo(cx, cy + r)
            lineTo(cx - r * 0.866f, cy + r * 0.5f)
            lineTo(cx - r * 0.866f, cy - r * 0.5f)
            close()
        }

        drawPath(
            path = path,
            color = GlobalIETAColor.PrimaryCyan,
            style = Stroke(width = 2.dp.toPx())
        )

        // Inner glowing core dot
        drawCircle(
            color = GlobalIETAColor.AuraBlue,
            radius = r * 0.35f,
            center = Offset(cx, cy)
        )

        // Cross line accents
        drawLine(
            color = GlobalIETAColor.ElectricBlue,
            start = Offset(cx - r * 0.5f, cy),
            end = Offset(cx + r * 0.5f, cy),
            strokeWidth = 1.5.dp.toPx()
        )
    }
}

@Composable
fun GlobalIetaLogo(
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    showTagline: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlobalIetaLogoIcon(size = iconSize)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = "GLOBAL IETA",
                style = GlobalIetaTheme.typography.cardTitle.copy(
                    color = GlobalIETAColor.PrimaryText
                )
            )
            if (showTagline) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "CONNECTED WORLDS · ONE FOUNDATION",
                    style = GlobalIetaTheme.typography.technicalLabel.copy(
                        color = GlobalIETAColor.PrimaryCyan
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaLogoPreview() {
    GlobalIetaTheme {
        GlobalIetaLogo()
    }
}
