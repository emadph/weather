import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

object Config {
    const val API_ROOT_TEST = "\"http://api.openweathermap.org/data/2.5/\""
    const val API_ROOT = "\"http://api.openweathermap.org/data/2.5/\""
    const val SECRET_KEY = "\"c37489058728f6774820d79553f9fdd7\""
    const val WEATHER_ICON_BASE_URL = "\"http://openweathermap.org/img/wn/\""

}

fun Project.configAndroid() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(Versions.Android.sdk)
    defaultConfig {
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.sdk
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = Dependencies.testInstrumentRunner

        buildConfigField("String", "API_ROOT", Config.API_ROOT)
        buildConfigField("String", "API_ROOT_TEST", Config.API_ROOT_TEST)
        buildConfigField("String", "SECRET_KEY", Config.SECRET_KEY)
        buildConfigField("String", "WEATHER_ICON_BASE_URL", Config.WEATHER_ICON_BASE_URL)

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}