object Config {
    const val compileSdk = 29
    const val minSdk = 23
    const val targetSdk = 29
    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val jvmTarget = "1.8"

    val compilerArgs = listOf(
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
    )

    // Flavors
    const val release = "release"
    const val debug = "debug"

    object Version {
        const val code = 1
        const val name = "1.0"
    }
}