package com.example.catapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.catapp.presentation.CatViewModel
import com.example.catapp.presentation.fragments.composables.CatList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFragment : Fragment() {

    private val catViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {

                val result = catViewModel.cat.value
                val loading = catViewModel.loading.value

                Scaffold {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(it)
                    ) {
                        CatList(
                            loading = loading,
                            cats = result
                        )
                    }

                }
            }
        }
    }

}