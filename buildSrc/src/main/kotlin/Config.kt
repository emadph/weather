import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

object Config {
    const val API_ROOT_TEST = "\"https://borsa.ir/\""
    const val API_ROOT = "\"https://borsa.ir/\""
    const val SECRET_KEY = "\"FxLwq!\$%S!\$@#}#EA(4Sd#\""

}

fun Project.configAndroid() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(Versions.Android.sdk)
    defaultConfig {
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.sdk
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = Dependencies.testInstrumentRunner
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_ROOT", Config.API_ROOT)
        buildConfigField("String", "API_ROOT_TEST", Config.API_ROOT_TEST)
        buildConfigField("String", "SECRET_KEY", Config.SECRET_KEY)

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