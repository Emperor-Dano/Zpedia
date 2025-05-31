package com.danielaqs.zpedia.data.remote

import com.danielaqs.zpedia.data.remote.model.Character
import com.danielaqs.zpedia.data.remote.model.CharacterDetail
import com.danielaqs.zpedia.data.remote.model.CharacterList
import com.danielaqs.zpedia.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    //Aquí van los endpoints
    /*
        TODOS los personajes
        https://dragonball-api.com/api/characters?limit=58

        Detalle de personaje
        https://dragonball-api.com/api/characters/id
    */

    @GET(Constants.ENDPOINT_ALL)
    suspend fun getCharacters(
        @Query(Constants.LIMIT) limit: Int = Constants.LIMIT_VAL
    ): CharacterList

    @GET(Constants.ENDPOINT_DETAIL)
    suspend fun getCharacterDetail(
        @Path(Constants.ID) id: Int?
    ): CharacterDetail

}