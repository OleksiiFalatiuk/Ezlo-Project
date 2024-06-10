package com.example.ezloproject.ui.main.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ezloproject.R

@Composable
fun DeleteDialog(
    openDialog: Boolean,
    name: String,
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    if (openDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = onCancel,
            title = {
                DialogTitle(text = "Are you sure?")
            },
            text = {
                DialogText(text = "Do you want to delete $name?")
            },
            confirmButton = {
                DialogButton(
                    text = "Delete", // todo: use string resource
                    onClick = onDelete
                )
            },
            dismissButton = {
                DialogButton(
                    text = "Cancel", // todo: use string resource
                    onClick = onCancel
                )
            }
        )
    }
}

@Composable
private fun DialogTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        color = colorResource(id = R.color.text),
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
    )
}

@Composable
private fun DialogText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        color = colorResource(id = R.color.text),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
    )
}

@Composable
private fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = colorResource(id = R.color.text),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
        )
    }
}

@Preview
@Composable
private fun PreviewDeleteDialog() {
    var openDialog by remember { mutableStateOf(true) }
    DeleteDialog(
        name = "Item",
        openDialog = openDialog,
        onDelete = { openDialog = false },
        onCancel = { openDialog = false }
    )
}

@Preview
@Composable
private fun PreviewDialogButton() {
    DialogButton(text = "Delete")
}
