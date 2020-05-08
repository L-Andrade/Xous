plugins {
    id("com.android.application")
    defaultPlugins()
}

android {
    default()

    defaultConfig {
        applicationId = "com.andradel.xous"
    }

    signingConfigs {
        create(Config.release) {
            storeFile = rootProject.file("release.keystore")
            keyAlias = "Xous"
            storePassword = System.getenv("XOUSPASSWORD")
            keyPassword = System.getenv("XOUSPASSWORD")
        }
    }

    buildTypes {
        getByName(Config.release) {
            signingConfig = signingConfigs.getByName(Config.release)
        }
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(project(Modules.home))
    implementation(project(Modules.showProfile))
    implementation(project(Modules.search))
}
