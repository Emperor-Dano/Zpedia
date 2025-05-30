package com.danielaqs.zpedia.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielaqs.zpedia.databinding.CharacterElementBinding
import com.danielaqs.zpedia.data.remote.model.Character
import com.danielaqs.zpedia.data.remote.model.CharacterList

class CharactersAdapter(
    private val characters: List<Character>,
    private val onCharClick: (Character) -> Unit
): RecyclerView.Adapter<CharViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        val character = characters[position]

        holder.bind(character)

        holder.itemView.setOnClickListener {
            onCharClick(character)
        }
    }
}