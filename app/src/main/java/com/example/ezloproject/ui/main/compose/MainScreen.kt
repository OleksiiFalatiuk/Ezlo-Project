package com.example.ezloproject.ui.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ezloproject.R
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.ui.views.HeaderView
import com.example.ezloproject.ui.views.ItemView

@Composable
fun MainScreen(
    itemsData: SnapshotStateList<ItemEntity>
){
    val items = remember { itemsData }
    Column(Modifier.background(color = colorResource(id = R.color.primary))) {
        LazyColumn{
            item {
                HeaderView()
            }
            items(items){
                ItemView(itemEntity = it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
    val deminingData = remember { mutableStateListOf<ItemEntity>() }
    MainScreen(deminingData)
}