package com.example.rickyandmorty_michaelzhong.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickyandmorty_michaelzhong.R
import com.example.rickyandmorty_michaelzhong.databinding.CharacterItemLayoutBinding
import com.example.rickyandmorty_michaelzhong.model.data.Character

class CharacterAdapter(private val characterSelectionDelegate: CharacterSelectionDelegate): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    inner class CharacterViewHolder(val binding: CharacterItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface CharacterSelectionDelegate {
        fun selectCharacter(character: Character)
    }

    var characters: List<Character> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.apply {
            binding.nameTextview.text = character.name
            Glide.with(binding.root)
                .applyDefaultRequestOptions(RequestOptions().fitCenter())
                .load(character.image)
                .into(binding.characterImageview)
            binding.root.setOnClickListener{
                characterSelectionDelegate.selectCharacter(character)
            }
        }
    }

    override fun getItemCount(): Int = characters.size
}