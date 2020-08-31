object Versions {
    const val kotlin = "1.4.0"
    const val gradle = "4.0.1"
    const val appCompat = "1.2.0"
    const val androidX = "1.3.0"
    const val constraintLayout = "2.0.1"
    const val junit = "4.12"
    const val androidXjunit = "1.1.1"
    const val espresso = "3.2.0"
    const val nav = "2.3.0"
    const val material = "1.2.0"
    const val room = "2.2.5"
    const val retrofit = "2.9.0"
    const val serialization = "1.0.0-RC"
    const val serializationAdapter = "0.6.0"
    const val dagger = "2.28"
    const val viewModel = "2.2.0"
    const val okHttpInterceptor = "4.8.1"
    const val glide = "4.11.0"
    const val flexbox = "2.0.1"
    const val coroutines = "1.3.8"
    const val androidXfragment = "1.2.4"
    const val indicator = "2.1.4"
    const val photoView = "2.3.0"
    const val google = "4.3.3"
    const val recyclerView = "1.2.0-alpha03"
    const val crashlytics = "2.2.1"

    object Firebase {
        const val analytics = "17.5.0"
        const val crashlytics = "17.2.1"
    }
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val commonModels = ":commonmodels"
    const val commonUi = ":commonui"
    const val db = ":database"

    // Features
    const val home = ":home"
    const val showProfile = ":showprofile"
    const val search = ":search"
}

object Libraries {

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.androidX}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.androidXfragment}"
        const val material = "com.google.android.material:material:${Versions.material}"

        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
        const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"

        // const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidXjunit = "androidx.test.ext:junit:${Versions.androidXjunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val annotations = "androidx.room:room-compiler:${Versions.room}"
        const val extensions = "androidx.room:room-ktx:${Versions.room}"
        const val testing = "androidx.room:room-testing:${Versions.room}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}"
        const val serializationAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serializationAdapter}"
        const val okHttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"
    }

    object Firebase {
        const val analytics =
            "com.google.firebase:firebase-analytics:${Versions.Firebase.analytics}"
        const val crashlytics =
            "com.google.firebase:firebase-crashlytics:${Versions.Firebase.crashlytics}"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAnnotations = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val flexbox = "com.google.android:flexbox:${Versions.flexbox}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val indicator = "me.relex:circleindicator:${Versions.indicator}"

    const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"
}

object Classpaths {
    const val buildTools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav}"
    const val googleServices = "com.google.gms:google-services:${Versions.google}"
    const val crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics}"
}

object Plugins {
    const val kapt = "kapt"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val serialization = "kotlinx-serialization"
}