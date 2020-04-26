plugins {
    libDefault()
    kotlin(Plugins.kapt)
}

android {
    default()

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
    implementation(Libraries.Android.androidXCore)
    api(Libraries.Room.room)
    implementation(Libraries.Room.roomExtensions)
    kapt(Libraries.Room.roomAnnotations)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerAnnotations)
    implementation(Libraries.coroutines)
    implementation(project(Modules.commonModels))
    testImplementation(Libraries.Testing.junit)
    androidTestImplementation(Libraries.Testing.androidXjunit)
    androidTestImplementation(Libraries.Room.roomTesting)
}
