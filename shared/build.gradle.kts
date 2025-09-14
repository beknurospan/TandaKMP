
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.koin.androidx.compose)
            api(libs.ktor.client.okhttp)
            api(libs.androidx.room.sqlite.wrapper)
        }
        commonMain.dependencies {

            api(libs.kotlinx.serialization.json)

            api(libs.koin.core)
            api(libs.ktor.client.core)

            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.serialization.kotlinx.json)
            api(libs.ktor.client.logging)


            api(libs.androidx.room.runtime)
            api(libs.androidx.sqlite.bundled)


            api(libs.androidx.datastore)
            api(libs.androidx.datastore.preferences)



        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.beknur.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    testOptions {
        targetSdk = 34
    }
    lint {
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)

}
