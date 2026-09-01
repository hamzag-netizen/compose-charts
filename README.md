# 📱 compose-charts

Beautiful, animated charting library for Jetpack Compose with Material 3 theming and gesture support.

[![Maven Central](https://img.shields.io/badge/maven-v0.9.0-7F52FF)](https://search.maven.org)
[![License: Apache-2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF.svg)](https://kotlinlang.org)

## Features

- 📊 **Chart Types** — Line, Bar, Pie, Donut, Radar, Scatter, Area
- 🎨 **Material 3** — Automatic theming with dynamic colors
- ✨ **Animations** — Smooth spring-based entry and transition animations
- 👆 **Gestures** — Pinch-to-zoom, pan, and tap for data point selection
- ♿ **Accessible** — TalkBack support and semantic descriptions

## Quick Start

```kotlin
// build.gradle.kts
dependencies {
    implementation("dev.hamzagunes:compose-charts:0.9.0")
}
```

```kotlin
@Composable
fun SalesChart() {
    val data = remember {
        lineChartData {
            series("Revenue") {
                points(listOf(12f, 28f, 35f, 42f, 58f, 64f, 72f))
                color(MaterialTheme.colorScheme.primary)
            }
            series("Expenses") {
                points(listOf(8f, 15f, 22f, 30f, 35f, 40f, 45f))
                color(MaterialTheme.colorScheme.secondary)
            }
            xLabels("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        }
    }

    LineChart(
        data = data,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp),
        animation = ChartAnimation.Spring(
            dampingRatio = 0.7f,
            stiffness = 200f
        ),
        interaction = ChartInteraction.PanAndZoom,
    )
}
```

## Chart Types

| Chart      | Preview | Features                          |
|-----------|---------|-----------------------------------|
| LineChart | 📈      | Multi-series, gradients, markers  |
| BarChart  | 📊      | Stacked, grouped, horizontal      |
| PieChart  | 🥧      | Donut variant, labels, explode    |
| RadarChart| 🕸️      | Multi-axis, filled, outlined      |

## License

Apache License 2.0
