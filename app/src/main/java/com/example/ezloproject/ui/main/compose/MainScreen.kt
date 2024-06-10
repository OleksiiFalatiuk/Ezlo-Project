package com.example.ezloproject.ui.main.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.ui.IconMapper
import com.example.ezloproject.ui.views.HeaderView
import com.example.ezloproject.ui.views.ItemView
import com.example.ezloproject.ui.views.ResetButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    itemsData: SnapshotStateList<ItemEntity>,
    onItemDelete: (ItemEntity) -> Unit = {},
    onItemClick: (id: Int, isEditMode: Boolean) -> Unit = { _, _ ->},
    onResetButtonClick: () -> Unit = {}
) {
    val items = remember { itemsData }
    val iconMapper: IconMapper = remember { IconMapper() }
    val selectedItem = remember { mutableStateOf<ItemEntity?>(null) }
    val openDeleteDialog by remember {
        derivedStateOf { selectedItem.value != null }
    }
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
                        selectedItem.value = it
                    },
                    onClick = { isEditMode ->
                        onItemClick(it.id, isEditMode)
                    },
                )
            }
        }
    }
    DeleteDialog(
        openDialog = openDeleteDialog,
        name = selectedItem.value?.platform.orEmpty(),
        onDelete = {
            selectedItem.value?.let {
                onItemDelete(it)
                selectedItem.value = null
            }
        },
        onCancel = {
            selectedItem.value = null
        }
    )
}

@Preview
@Composable
fun PreviewMainScreen() {
    val deminingData = remember { mutableStateListOf<ItemEntity>() }
    deminingData.addAll(
        List(10) {
            ItemEntity(
                pkDevice = it,
                macAddress = "macAddress $it",
                firmware = "firmware $it",
                platform = "platform $it",
                title = "title $it"
            )
        }
    )
    MainScreen(deminingData)
}
