package com.andradel.xous.common_models

sealed class ImageSize(val size: String) {
    sealed class Backdrop(size: String) : ImageSize(size) {
        object Small : Backdrop("w300")
        object Medium : Backdrop("w780")
        object Big : Backdrop("w1280")
    }

    sealed class Poster(size: String) : ImageSize(size) {
        object Small : Poster("w342")
        object Medium : Poster("w500")
        object Big : Poster("w500")
    }

    sealed class Profile(size: String) : ImageSize(size) {
        object Small : Profile("w45")
        object Medium : Profile("w185")
        object Big : Profile("h632")
    }

    object Original : ImageSize("original")

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
    }
}

fun String?.toImagePath(size: ImageSize = ImageSize.Original): String? =
    if (this == null) null else "${ImageSize.BASE_IMAGE_URL}${size.size}$this"