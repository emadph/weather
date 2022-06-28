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

    const val eventBus = "org.greenrobot:eventbus:${Versions.eventBus}"

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

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val extJunitKtx = "androidx.test.ext:junit-ktx:${Versions.extJunit}"
        const val coreTest = "androidx.test:core:${Versions.coreTest}"
        const val coreTestKtx = "androidx.test:core-ktx:${Versions.coreTest}"
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val extTruth = "androidx.test.ext:truth:${Versions.extTruth}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val okhttp = "com.squareup.okhttp3:mockwebserver3:${Versions.okhttp}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"

        const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val hiltKaptTesting = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val runner = "androidx.test:runner:${Versions.testRunner}"
        const val rules = "androidx.test:rules:${Versions.rules}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val navigationTesting =
            "androidx.navigation:navigation-testing:${Versions.navigationTesting}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"


        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        const val espressoAccessibility =
            "androidx.test.espresso:espresso-accessibility:${Versions.espresso}"
        const val espressoWeb = "androidx.test.espresso:espresso-web:${Versions.espresso}"
        const val espressoConcurrent =
            "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"
        const val espressoIdleResource =
            "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"

        const val mockito = "org.mockito:mockito-android:${Versions.mockito}"
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
        val testImplementation by configurations
        val androidTestImplementation by configurations

        implementation(Dependencies.AndroidX.coreKtx)
        implementation(Dependencies.AndroidX.appCompat)
        implementation(Dependencies.AndroidX.constraintLayout)
        implementation(Dependencies.AndroidX.swipeRefreshLayout)
        implementation(Dependencies.material)

        implementation(Dependencies.Logging.logger)

        // For instrumentation tests
        androidTestImplementation(Dependencies.Test.junit)
        androidTestImplementation(Dependencies.Test.extJunit)
        androidTestImplementation(Dependencies.Test.extJunitKtx)
        androidTestImplementation(Dependencies.Test.truth)
        androidTestImplementation(Dependencies.Test.extTruth)
        androidTestImplementation(Dependencies.Test.coreTest)
        androidTestImplementation(Dependencies.Test.coreTestKtx)
        androidTestImplementation(Dependencies.Test.coreTesting)

        androidTestImplementation(Dependencies.Test.espressoCore)
        androidTestImplementation(Dependencies.Test.espressoContrib)
        androidTestImplementation(Dependencies.Test.espressoIntents)
        androidTestImplementation(Dependencies.Test.espressoAccessibility)
        androidTestImplementation(Dependencies.Test.espressoConcurrent)
        androidTestImplementation(Dependencies.Test.espressoIdleResource)
        androidTestImplementation(Dependencies.Test.espressoWeb)
        androidTestImplementation(Dependencies.Test.navigationTesting)
        "debugImplementation"(Dependencies.Test.fragmentTesting)

        androidTestImplementation(Dependencies.Test.hiltTesting)
        "kaptAndroidTest"(Dependencies.Test.hiltKaptTesting)
        androidTestImplementation(Dependencies.Test.runner)
        androidTestImplementation(Dependencies.Test.coroutines)
        androidTestImplementation(Dependencies.Test.okhttp)
        androidTestImplementation(Dependencies.Test.mockk)
        androidTestImplementation(Dependencies.Test.mockito)

        // For local unit tests
        testImplementation(Dependencies.Test.junit)
        testImplementation(Dependencies.Test.coroutines)
        testImplementation(Dependencies.Test.truth)
        testImplementation(Dependencies.Test.extTruth)
        testImplementation(Dependencies.Test.mockito)
        testImplementation(Dependencies.Test.okhttp)
        testImplementation(Dependencies.Test.mockk)
        testImplementation(Dependencies.Test.robolectric)


    }
}