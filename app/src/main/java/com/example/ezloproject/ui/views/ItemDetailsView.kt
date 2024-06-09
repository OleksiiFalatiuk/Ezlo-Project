package com.example.ezloproject.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.locale.ItemEntity

@Composable
fun ItemDetailsView(itemEntity: ItemEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = colorResource(id = R.color.primary))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = colorResource(id = R.color.accent))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.wrapContentSize()) {
                Text(
                    text = itemEntity.title,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text),
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "SN: ${itemEntity.pkDevice}",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.disabled),
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = colorResource(id = R.color.disabled),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.text))
        )
    }
}

@Preview
@Composable
fun PreviewItemDetailsView() {
    ItemView(
        itemEntity = ItemEntity(
            1,
            45013855,
            "platform",
            "title"
        )
    )
}