import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Classpaths.buildTools)
        classpath(Classpaths.gradle)
        classpath(Classpaths.serialization)
        classpath(Classpaths.safeArgs)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }

    gradle.projectsEvaluated {
        tasks.withType(KotlinCompile::class).all {
            kotlinOptions.freeCompilerArgs = Config.compilerArgs
            kotlinOptions.jvmTarget = Config.jvmTarget
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
