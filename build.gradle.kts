plugins {
    id("com.android.library") version "8.2.0"
    id("org.jetbrains.kotlin.android") version "1.9.20"
}

android {
    namespace = "dev.hamzagunes.composecharts"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.5" }
    buildFeatures { compose = true }
}
