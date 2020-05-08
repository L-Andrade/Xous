package com.andradel.xous.search.model

import com.andradel.xous.commonmodels.ImageSize
import com.andradel.xous.commonmodels.external.show.ShowExternal
import com.andradel.xous.commonmodels.internal.person.RegularPerson
import com.andradel.xous.commonmodels.orZero
import com.andradel.xous.commonmodels.toImagePath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegularPersonExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("known_for_department") val department: String? = null,
    @SerialName("known_for") val knownFor: List<ShowExternal>? = null
) {
    fun toInternal(): RegularPerson = RegularPerson(
        id = id.orZero(),
        name = name.orEmpty(),
        profileUrl = profilePath.toImagePath(ImageSize.Profile.Medium),
        department = department.orEmpty(),
        knownFor = knownFor.orEmpty().map { it.toInternal() }
    )
}