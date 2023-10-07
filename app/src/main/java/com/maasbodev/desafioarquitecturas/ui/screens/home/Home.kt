package com.maasbodev.desafioarquitecturas.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.maasbodev.desafioarquitecturas.data.MarvelRepository
import com.maasbodev.desafioarquitecturas.data.entities.MarvelItem
import com.maasbodev.desafioarquitecturas.ui.theme.DesafioArquitecturasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(marvelRepository: MarvelRepository) {
	DesafioArquitecturasTheme {
		val viewModel: HomeViewModel = viewModel { HomeViewModel(marvelRepository) }
		val state by viewModel.state.collectAsState()

		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			Scaffold(
				topBar = { TopAppBar(title = { Text("Characters") }) }
			) { padding ->
				if (state.loading) {
					Box(
						modifier = Modifier.fillMaxSize(),
						contentAlignment = Alignment.Center
					) {
						CircularProgressIndicator()
					}
				}

				if (state.characters.isNotEmpty()) {
					LazyVerticalGrid(
						columns = GridCells.Adaptive(120.dp),
						modifier = Modifier.padding(padding),
						horizontalArrangement = Arrangement.spacedBy(4.dp),
						verticalArrangement = Arrangement.spacedBy(4.dp),
						contentPadding = PaddingValues(4.dp),
					) {
						items(state.characters) { movie ->
							CharacterItem(
								marvelItem = movie,
								onClick = { viewModel.onCharacterClick(movie) }
							)
						}
					}
				}
			}
		}
	}
}

@Composable
fun CharacterItem(marvelItem: MarvelItem, onClick: () -> Unit) {
	Column(
		modifier = Modifier.clickable(onClick = onClick)
	) {
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			Box {
				AsyncImage(
					model = marvelItem.thumbnail,
					contentDescription = marvelItem.title,
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.fillMaxWidth()
						.background(Color.LightGray)
						.aspectRatio(1f),
				)
				if (marvelItem.favorite) {
					Icon(
						imageVector = Icons.Default.Favorite,
						contentDescription = "Favorite",
						modifier = Modifier
							.align(Alignment.TopEnd)
							.padding(8.dp),
						tint = Color.White
					)
				}
			}
			Spacer(modifier = Modifier.height(16.dp))
			Text(
				text = marvelItem.title,
				modifier = Modifier
					.padding(16.dp)
					.height(48.dp),
				maxLines = 2,
			)
		}
	}
}