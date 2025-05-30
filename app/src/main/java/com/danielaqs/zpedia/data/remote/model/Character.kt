package com.danielaqs.zpedia.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("items") val items: List<Character>
)

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("affiliation") val affiliation: String
)
