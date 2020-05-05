plugins {
    libDefault()
    id(Plugins.serialization)
}

android {
    default()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlin)
    implementation(Libraries.Network.serialization)
}
