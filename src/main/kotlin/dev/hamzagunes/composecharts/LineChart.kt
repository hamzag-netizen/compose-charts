package dev.hamzagunes.composecharts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

data class ChartSeries(
    val label: String,
    val points: List<Float>,
    val color: Color = Color.Blue,
)

data class LineChartData(
    val series: List<ChartSeries>,
    val xLabels: List<String> = emptyList(),
)

@Composable
fun LineChart(
    data: LineChartData,
    modifier: Modifier = Modifier,
    lineWidth: Float = 3f,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val padding = 40f

        for (series in data.series) {
            if (series.points.isEmpty()) continue

            val maxVal = series.points.max()
            val minVal = series.points.min()
            val range = (maxVal - minVal).coerceAtLeast(1f)

            val path = Path()
            val stepX = (width - padding * 2) / (series.points.size - 1).coerceAtLeast(1)

            series.points.forEachIndexed { index, value ->
                val x = padding + index * stepX
                val y = height - padding - ((value - minVal) / range) * (height - padding * 2)

                if (index == 0) path.moveTo(x, y)
                else path.lineTo(x, y)
            }

            drawPath(
                path = path,
                color = series.color,
                style = Stroke(width = lineWidth),
            )
        }
    }
}
