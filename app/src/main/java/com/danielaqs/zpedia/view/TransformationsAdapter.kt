package com.danielaqs.zpedia.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielaqs.zpedia.data.remote.model.Transformation
import com.danielaqs.zpedia.databinding.TransformationElementBinding

class TransformationsAdapter(
    private val transformations: List<Transformation>,
    private val onCharClick: (Transformation) -> Unit
): RecyclerView.Adapter<TransViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransViewHolder {
        val binding = TransformationElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransViewHolder(binding)
    }

    override fun getItemCount(): Int = transformations.size

    override fun onBindViewHolder(holder: TransViewHolder, position: Int) {
        val transformation = transformations[position]

        holder.bind(transformation)

    }
}