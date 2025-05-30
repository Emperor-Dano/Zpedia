package com.danielaqs.zpedia.data.remote

import com.danielaqs.zpedia.data.remote.model.Character
import com.danielaqs.zpedia.data.remote.model.CharacterDetail
import com.danielaqs.zpedia.data.remote.model.CharacterList
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

    @GET("api/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 58
    ): CharacterList

    @GET("api/characters/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int?
    ): CharacterDetail

}