package com.example.catapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catapp.presentation.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFragment : Fragment() {

    private val catViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        catViewModel
        return ComposeView(requireContext()).apply{
            setContent {
            }
        }
    }

}