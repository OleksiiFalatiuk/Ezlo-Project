package com.example.ezloproject.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezloproject.R
import com.example.ezloproject.data.model.locale.ItemEntity
import com.example.ezloproject.ui.IconMapper

@Composable
fun ItemDetailsView(
    itemEntity: ItemEntity?,
    isEditMode: Boolean,
    onUpdatedTitleSaveClick: (title: String?, id: Int?) -> Unit = { _, _ -> }
) {
    val iconMapper: IconMapper = remember { IconMapper() }
    val updatedTitle = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        updatedTitle.value = itemEntity?.title?.ifEmpty {
            itemEntity.platform
        }.toString()
    }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = colorResource(id = R.color.primary))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = colorResource(id = R.color.accent))
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(iconMapper.getIcon(itemEntity?.platform ?: "")),
                    tint = colorResource(id = R.color.text),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (isEditMode) {
                UserDataItem(updatedTitle = updatedTitle)
            } else {
                Text(
                    text = itemEntity?.title ?: "",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.text),
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
                )
            }
        }
        if (isEditMode) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        color = colorResource(id = R.color.accent),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        onUpdatedTitleSaveClick(updatedTitle.value, itemEntity?.id)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold))
                )
            }
        }
        ItemDetailsTextView("SN: ${itemEntity?.pkDevice}")
        ItemDetailsTextView("MAC Address: ${itemEntity?.macAddress}")
        ItemDetailsTextView("Firmware: ${itemEntity?.firmware}")
        ItemDetailsTextView("Model: ${itemEntity?.platform}")
    }
}

@Composable
fun ItemDetailsTextView(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
        color = colorResource(id = R.color.disabled),
        overflow = TextOverflow.Ellipsis,
        text = text,
    )
}

@Composable
fun UserDataItem(updatedTitle: MutableState<String>) {
    val lightBlue = Color(0xffd8e6ff)
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = updatedTitle.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                updatedTitle.value = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}

@Preview
@Composable
fun PreviewItemDetailsView() {
    ItemDetailsView(
        itemEntity = ItemEntity(
            pkDevice = 0,
            macAddress = "macAddress",
            firmware = "firmware",
            platform = "TEST TEST TEST",
            title = "title"
        ),
        isEditMode = true,
    )
}