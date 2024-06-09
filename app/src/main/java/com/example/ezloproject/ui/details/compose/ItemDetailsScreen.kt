package com.example.ezloproject.ui.details.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.ui.views.HeaderView
import com.example.ezloproject.ui.views.ItemDetailsView

@Composable
fun ItemDetailsScreen(itemEntity: ItemEntity) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.primary)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderView()
        ItemDetailsView(itemEntity = itemEntity)
    }
}

@Preview
@Composable
fun PreviewItemDetailsScreen() {
//    ItemDetailsScreen()
}