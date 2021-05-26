package com.example.rickyandmorty_michaelzhong.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.rickyandmorty_michaelzhong.R
import com.example.rickyandmorty_michaelzhong.databinding.ActivityMainBinding
import com.example.rickyandmorty_michaelzhong.model.data.Character
import com.example.rickyandmorty_michaelzhong.util.Logger.Companion.logDebug
import com.example.rickyandmorty_michaelzhong.view.adapter.CharacterAdapter
import com.example.rickyandmorty_michaelzhong.viewmodel.RaMViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity(), CharacterAdapter.CharacterSelectionDelegate {

    private val raMViewModel: RaMViewModel by viewModels()
    private val adapter: CharacterAdapter = CharacterAdapter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.characterRecyclerview.adapter = adapter

        binding.retryButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            raMViewModel.getCharacters()
            binding.retryButton.visibility = View.GONE
        }

        raMViewModel.getCharacters()

        raMViewModel.getData().observe(this, { characters ->

            binding.loading.visibility = View.GONE
            binding.retryButton.visibility = View.GONE

            characters?.let {
                adapter.characters = it
            }?: showError()
        })
    }

    private fun showError() {
        Snackbar.make(binding.root, "An Error occurred!", Snackbar.LENGTH_SHORT).show()
        binding.retryButton.visibility = View.VISIBLE
    }

    override fun selectCharacter(character: Character) {

    }
}