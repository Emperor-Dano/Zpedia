package com.danielaqs.zpedia.data.remote.model

import com.danielaqs.zpedia.utils.Constants
import com.google.gson.annotations.SerializedName


data class CharacterDetail(
    @SerializedName(Constants.ID) val id: Int,
    @SerializedName(Constants.NAME) val name: String,
    @SerializedName(Constants.IMAGE) val image: String,
    @SerializedName(Constants.KI) val ki: String,
    @SerializedName(Constants.MAXKI) val maxKi: String,
    @SerializedName(Constants.RACE) val race: String,
    @SerializedName(Constants.GENDER) val gender: String,
    @SerializedName(Constants.DESC) val description: String,
    @SerializedName(Constants.AFFILIATION) val affiliation: String,
    @SerializedName(Constants.TRANSFORMATIONS) val transformations: List<Transformation>
)

data class Transformation(
    @SerializedName(Constants.NAME) val name: String,
    @SerializedName(Constants.IMAGE) val image: String?,
    @SerializedName(Constants.KI) val ki: String? = null
)