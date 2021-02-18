plugins {
    libDefault()
    kotlin(Plugins.kapt)
    id(Plugins.anvil) version Versions.anvil
}

android {
    xous()

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(
                    "room.incremental" to "true",
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
    }

    sourceSets {
        getByName("test").assets.srcDir("$projectDir/schemas")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Android.appCompat)
    implementation(Libraries.Android.core)
    api(Libraries.Room.core)
    implementation(Libraries.Room.extensions)
    kapt(Libraries.Room.annotations)
    implementation(Libraries.dagger)
    implementation(project(Modules.scopes))
    implementation(Libraries.coroutines)
    implementation(project(Modules.commonModels))
    testImplementation(Libraries.Testing.junit)
    androidTestImplementation(Libraries.Testing.androidXjunit)
    androidTestImplementation(Libraries.Room.testing)
}

anvil {
    generateDaggerFactories = true // default is false
}