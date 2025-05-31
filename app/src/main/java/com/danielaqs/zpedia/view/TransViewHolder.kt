package com.danielaqs.zpedia.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielaqs.zpedia.databinding.CharacterElementBinding
import com.danielaqs.zpedia.data.remote.model.Character
import com.danielaqs.zpedia.data.remote.model.Transformation
import com.danielaqs.zpedia.databinding.TransformationElementBinding


class TransViewHolder(
    private val binding: TransformationElementBinding
): RecyclerView.ViewHolder(binding.root) {

    //Aquí se realizan las vinculaciones de las vistas

    fun bind(transformation: Transformation){

        binding.tName.text = transformation.name
        binding.tKi.text = transformation.ki

        Glide.with(binding.root.context)
            .load(transformation.image)
            .into(binding.tImage)
    }


}