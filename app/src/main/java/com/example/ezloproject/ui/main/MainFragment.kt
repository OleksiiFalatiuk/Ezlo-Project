package com.example.ezloproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.ezloproject.ui.main.compose.MainScreen
import com.example.ezloproject.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {

    private val mainViewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                MainScreen(mainViewModel.mainDataResult)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getItemData()
        initObservers()
    }

    private fun initObservers(){
        mainViewModel.errorMessage.observe(viewLifecycleOwner){ errorMessage ->
            context?.let { Toast.makeText(it, errorMessage.toString(), Toast.LENGTH_SHORT).show() }
        }
    }
}