package com.example.ezloproject.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezloproject.R

@Composable
fun ResetButton(
    modifier: Modifier = Modifier,
    onResetButtonClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.accent)),
        shape = RoundedCornerShape(8.dp),
        onClick = { onResetButtonClick() }) {
        Text(
            text = stringResource(id = R.string.reset),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold))
        )
    }
}

@Preview
@Composable
fun PreviewResetButton() {
    ResetButton()
}
