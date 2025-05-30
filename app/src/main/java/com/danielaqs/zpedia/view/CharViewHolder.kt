package com.danielaqs.zpedia.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielaqs.zpedia.databinding.CharacterElementBinding
import com.danielaqs.zpedia.data.remote.model.Character


class CharViewHolder(
    private val binding: CharacterElementBinding
): RecyclerView.ViewHolder(binding.root) {

    //Aquí se realizan las vinculaciones de las vistas

    fun bind(character: Character){

        binding.tvTitle.text = character.name
        binding.ChAffiliation.text = character.affiliation

        Glide.with(binding.root.context)
            .load(character.image)
            .into(binding.ivThumbnail)
    }


}