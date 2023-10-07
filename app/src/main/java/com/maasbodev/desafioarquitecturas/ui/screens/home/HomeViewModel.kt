package com.maasbodev.desafioarquitecturas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maasbodev.desafioarquitecturas.data.MarvelRepository
import com.maasbodev.desafioarquitecturas.data.entities.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MarvelRepository) : ViewModel() {

	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	init {
		viewModelScope.launch {
			_state.value = UiState(loading = true)
			repository.requestCharacters()

			repository.characters.collect {
				_state.value = UiState(characters = it)
			}
		}
	}

	fun onCharacterClick(character: Character) {
		viewModelScope.launch {
			repository.updateCharacter(character.copy(favorite = !character.favorite))
		}
	}

	data class UiState(
		val loading: Boolean = false,
		val characters: List<Character> = emptyList()
	)
}