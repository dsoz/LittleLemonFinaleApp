package com.sozonnikd.littlelemonfinaleapp.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sozonnikd.littlelemonfinaleapp.R

@Composable
fun RestaurantLogo(modifier: Modifier){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "restaurant logo",
        modifier = modifier
            .width(150.dp)
            .padding(10.dp)
    )
}