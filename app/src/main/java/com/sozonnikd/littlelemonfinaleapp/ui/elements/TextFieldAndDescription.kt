package com.sozonnikd.littlelemonfinaleapp.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sozonnikd.littlelemonfinaleapp.R
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography

@Composable
fun TextFieldAndDescription(value: String ): String{
    var returnText = ""
    Column() {
        Text(
            text = stringResource(id = R.string.email),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {input -> returnText = input},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
            shape = RoundedCornerShape(8.dp),
        )
    }
    return returnText
}