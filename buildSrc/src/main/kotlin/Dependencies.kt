import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

object Dependencies {

    const val testInstrumentRunner = "ir.pourahmadi.Weather.HiltTestRunner"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    // Activity KTX for viewModels()
    object ActivityKtx {
        const val activity = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.activityKtx}"
    }

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.core}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.core}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Coroutines.lifecycle}"
    }

    object Hilt {
        const val core = "com.google.dagger:hilt-android:${Versions.Hilt.core}"
        const val android = "com.google.dagger:hilt-compiler:${Versions.Hilt.core}"
        const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Hilt.lifecycle}"
        const val compiler = "androidx.hilt:hilt-compiler:${Versions.Hilt.compile}"

        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.core}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Logging {
        const val logger = "com.orhanobut:logger:${Versions.logger}"
    }


    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.ArchitectureComponents.room}"
        const val annotation = "androidx.room:room-compiler:${Versions.ArchitectureComponents.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.ArchitectureComponents.room}"
    }

}

fun Project.importCommonPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
    plugins.apply("kotlin-kapt")
}

// apply common plugin
fun Project.importCommonDependencies() {
    dependencies {

        "implementation"(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        "implementation"(Dependencies.Kotlin.stdLib)

        val implementation by configurations

        implementation(Dependencies.AndroidX.coreKtx)
        implementation(Dependencies.AndroidX.appCompat)
        implementation(Dependencies.AndroidX.constraintLayout)
        implementation(Dependencies.AndroidX.swipeRefreshLayout)
        implementation(Dependencies.material)

        implementation(Dependencies.Logging.logger)

    }
}