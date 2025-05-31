package com.danielaqs.zpedia.data.remote.model

import android.provider.ContactsContract.Contacts
import com.danielaqs.zpedia.utils.Constants
import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName(Constants.ITEMS) val items: List<Character>
)

data class Character(
    @SerializedName(Constants.ID) val id: Int,
    @SerializedName(Constants.NAME) val name: String,
    @SerializedName(Constants.IMAGE) val image: String,
    @SerializedName(Constants.AFFILIATION) val affiliation: String
)
