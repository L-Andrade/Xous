plugins {
    id("com.android.application")
    defaultPlugins()
}

android {
    default()

    defaultConfig {
        applicationId = "com.andradel.xous"
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(project(Modules.home))
    implementation(project(Modules.showProfile))
    implementation(project(Modules.search))
}
