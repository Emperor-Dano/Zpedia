package com.danielaqs.zpedia.utils

import com.danielaqs.zpedia.data.remote.model.Transformation
import com.google.gson.annotations.SerializedName

object Constants {
    const val BASE_URL = "https://dragonball-api.com/"
    const val LOGTAG = "APPLOGS"
    const val seleccionaste = "Seleccionaste: "
    const val ENDPOINT_ALL = "api/characters"
    const val ENDPOINT_DETAIL = "api/characters/{id}"
    const val LIMIT = "limit"
    const val LIMIT_VAL = 58

    //Variables a mapear a modelo
    const val ID = "id"
    const val NAME = "name"
    const val IMAGE = "image"
    const val KI = "ki"
    const val MAXKI = "maxKi"
    const val RACE = "race"
    const val GENDER = "gender"
    const val DESC = "description"
    const val AFFILIATION = "affiliation"
    const val TRANSFORMATIONS = "transformations"
    const val ITEMS = "items"
}