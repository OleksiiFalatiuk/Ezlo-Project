package com.example.ezloproject.ui.details.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezloproject.R
import com.example.ezloproject.ui.viewmodel.ItemDetailsViewModel
import com.example.ezloproject.ui.views.HeaderView
import com.example.ezloproject.ui.views.ItemDetailsView
import org.koin.androidx.compose.koinViewModel

@Composable
fun ItemDetailsScreen(
    context: Context = LocalContext.current,
    itemId: Int,
    isEditMode: Boolean,
    viewModel: ItemDetailsViewModel = koinViewModel(),
    navController: NavController? = null
) {
    LaunchedEffect(itemId) {
        viewModel.getItemById(itemId)
    }
    val itemData = remember { viewModel.itemResult }
    val isItemEditMode = remember { isEditMode }
    val updatedTitle = remember { viewModel.updatedTitle }
    val errorMessage = remember { viewModel.errorMessage }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderView()
        ItemDetailsView(
            itemEntity = itemData.value,
            isEditMode = isItemEditMode,
            updatedTitle = updatedTitle,
            onUpdatedTitleSaveClick = { title, itemId ->
                viewModel.updateItemTitle(title, itemId)
                navController?.popBackStack()
            }
        )
        errorMessage.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
fun PreviewItemDetailsScreen() {
    ItemDetailsScreen(
        itemId = 1,
        isEditMode = true
    )
}