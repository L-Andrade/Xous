plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
    google()
}

dependencies {
    // Seems like we get unresolved references in buildSrc module for its own values
    implementation("com.android.tools.build:gradle:3.6.3")
}