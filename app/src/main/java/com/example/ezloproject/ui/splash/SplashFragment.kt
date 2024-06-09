package com.example.ezloproject.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ezloproject.R
import com.example.ezloproject.ui.splash.compose.SplashScreen
import com.example.ezloproject.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: Fragment() {

    private val viewModel: SplashViewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            setContent {
                SplashScreen()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getItemData()
        initObservers()
    }

    private fun initObservers(){
        viewModel.apply {
            isResultSuccess.observe(viewLifecycleOwner){ isSuccess ->
                if (isSuccess == true){
                    findNavController().navigate(R.id.mainFragment)
                }
            }
            errorMessage.observe(viewLifecycleOwner){ message ->
                context?.let { Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show() }
            }
        }
    }
}