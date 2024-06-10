package com.example.ezloproject.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.local.ItemEntity
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemView(
    modifier: Modifier = Modifier,
    itemEntity: ItemEntity,
    @DrawableRes icon: Int,
    onClick: (isEditMode: Boolean) -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = colorResource(id = R.color.primary))
            .combinedClickable(
                onClick = { onClick(false) },
                onLongClick = onLongClick
            )
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
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(icon),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.wrapContentSize()) {
                Text(
                    text = itemEntity.title.ifEmpty {
                        itemEntity.platform
                    },
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
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = colorResource(id = R.color.accent),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        onClick(true)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.edit),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold))
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = colorResource(id = R.color.disabled),
                contentDescription = null
            )
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.text))
        )
    }
}

@Preview
@Composable
fun PreviewItemView() {
    ItemView(
        itemEntity = ItemEntity(
            id = Random.nextInt(),
            pkDevice = 1,
            macAddress = "macAddress",
            firmware = "firmware",
            platform = "platform",
            title = "title"
        ),
        icon = R.drawable.vera_edge_big
    )
}