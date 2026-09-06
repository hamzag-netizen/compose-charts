package dev.hamza.charts

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Data class representing a single pie chart segment.
 */
data class PieSlice(
    val label: String,
    val value: Float,
    val color: Color,
)

/**
 * A Material 3 styled pie chart with animated segments.
 *
 * @param slices The data slices to render.
 * @param modifier Modifier for the chart container.
 * @param strokeWidth Width of the pie ring (0 for filled pie).
 * @param animationDuration Duration of the entry animation in ms.
 * @param showLegend Whether to display a legend below the chart.
 */
@Composable
fun PieChart(
    slices: List<PieSlice>,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 0f,
    animationDuration: Int = 800,
    showLegend: Boolean = true,
) {
    val total = slices.sumOf { it.value.toDouble() }.toFloat()
    if (total == 0f) return

    // Animate sweep angle
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(slices) {
        animationProgress.snapTo(0f)
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = FastOutSlowInEasing,
            )
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        ) {
            var startAngle = -90f
            val chartSize = Size(size.width, size.height)

            slices.forEach { slice ->
                val sweepAngle = (slice.value / total) * 360f * animationProgress.value

                if (strokeWidth > 0f) {
                    drawArc(
                        color = slice.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        size = chartSize,
                        style = Stroke(width = strokeWidth),
                    )
                } else {
                    drawArc(
                        color = slice.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        size = chartSize,
                    )
                }

                startAngle += sweepAngle
            }
        }

        // Legend
        if (showLegend) {
            Spacer(modifier = Modifier.height(12.dp))
            slices.forEach { slice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp),
                ) {
                    Canvas(modifier = Modifier.size(12.dp)) {
                        drawCircle(color = slice.color)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${slice.label}: ${String.format("%.1f", slice.value / total * 100)}%",
                        fontSize = 13.sp,
                    )
                }
            }
        }
    }
}
