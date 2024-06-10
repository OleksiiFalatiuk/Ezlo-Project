package com.example.ezloproject.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ezloproject.ui.details.compose.ItemDetailsScreen
import com.example.ezloproject.ui.viewmodel.ItemDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailsFragment: Fragment() {

    private val viewModel: ItemDetailsViewModel by viewModel<ItemDetailsViewModel>()
    private val args: ItemDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.getItemById(args.itemId)
        return ComposeView(requireContext()).apply {
            setContent {
                ItemDetailsScreen(itemEntity = viewModel.itemResult, isEditMode = args.isEditMode, onUpdatedTitleSaveClick = viewModel::updateItemTitle)
            }
        }
    }
}