package com.sozonnikd.littlelemonfinaleapp.ui.elements


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography

@Composable
fun TextFieldInputAndDescription(inputValue: String,
                                 descriptionResource: Int,
                                 onValueChange: (String) -> Unit = {},
                                 readOnly: Boolean = false, ) {
    Text(
        text = stringResource(descriptionResource),
        style = Typography.h6,
        modifier = Modifier
            .padding(bottom = 5.dp))
    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        shape = RoundedCornerShape(8.dp),
    )

}
