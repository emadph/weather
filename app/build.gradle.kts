plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

importCommonPlugins()
configAndroid()
importCommonDependencies()

android {
    defaultConfig {
        applicationId = Versions.App.id

        testInstrumentationRunner = Dependencies.testInstrumentRunner

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.Hilt.core)
    "kapt"(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.lifecycle)
    "kapt"(Dependencies.Hilt.compiler)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLogging)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.gsonConverter)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    "kapt"(Dependencies.Room.annotation)

    implementation(Dependencies.Glide.glide)
    "kapt"(Dependencies.Glide.compiler)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Coroutines.lifecycle)

    implementation(Dependencies.Lifecycle.runtime)
    implementation(Dependencies.Lifecycle.viewmodel)

    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)

    implementation(Dependencies.ActivityKtx.activity)
    implementation(Dependencies.ActivityKtx.fragment)

    implementation(Dependencies.lottie)

    implementation(Dependencies.eventBus)

    //implementation(Dependencies.Epoxy.core)
    //implementation(Dependencies.Epoxy.paging)
    //implementation(Dependencies.Epoxy.databinding)
    //"kapt"(Dependencies.Epoxy.processor)

}
