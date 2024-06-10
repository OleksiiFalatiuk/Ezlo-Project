package com.example.ezloproject.ui.main.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.local.ItemEntity
import com.example.ezloproject.ui.IconMapper
import com.example.ezloproject.ui.viewmodel.MainViewModel
import com.example.ezloproject.ui.views.HeaderView
import com.example.ezloproject.ui.views.ItemView
import com.example.ezloproject.ui.views.ResetButton
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun MainScreen(
    context: Context = LocalContext.current,
    viewModel: MainViewModel = koinViewModel(),
    onItemClick: (id: Int, isEditMode: Boolean) -> Unit = { _, _ -> },
    onLoadData: () -> Unit = {}
) {
    LaunchedEffect(Unit){
        viewModel.getItemData()
    }
    val items = remember { viewModel.mainDataResult }
    val onLoadDataCallback = remember {
        derivedStateOf { items.size > 0 }
    }
    val iconMapper: IconMapper = remember { IconMapper() }
    val selectedItem = remember { mutableStateOf<ItemEntity?>(null) }
    val openDeleteDialog by remember {
        derivedStateOf { selectedItem.value != null }
    }
    val errorMessage = remember { viewModel.errorMessage }
    Screen(
        items = items,
        iconMapper = iconMapper,
        onItemDelete = {
            selectedItem.value = it
        },
        onItemClick = onItemClick,
        onResetButtonClick = viewModel::insertItemData
    )
    DeleteDialog(
        openDialog = openDeleteDialog,
        name = selectedItem.value?.platform.orEmpty(),
        onDelete = {
            selectedItem.value?.let {
                viewModel.deleteItem(it)
                selectedItem.value = null
            }
        },
        onCancel = {
            selectedItem.value = null
        }
    )
    errorMessage.value?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
    LaunchedEffect(onLoadDataCallback.value) {
        if (onLoadDataCallback.value) onLoadData()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Screen(
    items: SnapshotStateList<ItemEntity>,
    iconMapper: IconMapper,
    onResetButtonClick: () -> Unit,
    onItemClick: (Int, Boolean) -> Unit,
    onItemDelete: (ItemEntity) -> Unit
) {
    Column(Modifier.background(color = colorResource(id = R.color.primary))) {
        LazyColumn {
            item {
                HeaderView()
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                ResetButton(onResetButtonClick = onResetButtonClick)
            }
            items(items) {
                ItemView(
                    modifier = Modifier.animateItemPlacement(),
                    itemEntity = it,
                    icon = iconMapper.getIcon(it.platform),
                    onLongClick = {
                        onItemDelete(it)
                    },
                    onClick = { isEditMode ->
                        onItemClick(it.id, isEditMode)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val itemData = remember { mutableStateListOf<ItemEntity>() }
    itemData.addAll(
        List(10) {
            ItemEntity(
                id = Random.nextInt(),
                pkDevice = it,
                macAddress = "macAddress $it",
                firmware = "firmware $it",
                platform = "platform $it",
                title = "title $it"
            )
        }
    )
    Screen(
        items = itemData,
        iconMapper = IconMapper(),
        onResetButtonClick = {},
        onItemClick = { _, _ -> },
        onItemDelete = {}
    )
}
