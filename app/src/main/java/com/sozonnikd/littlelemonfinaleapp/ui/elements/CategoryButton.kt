package com.sozonnikd.littlelemonfinaleapp.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozonnikd.littlelemonfinaleapp.ui.theme.GreyHighlight
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography

@Composable
fun CategoryButton(onClick: () -> Unit, categoryName:  String){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = GreyHighlight),
        contentPadding = PaddingValues(10.dp),
        shape = RoundedCornerShape(15.dp),
    ) {
        Text(
            text = categoryName,
            fontSize = 20.sp,
            style = Typography.button,
        )
    }
}