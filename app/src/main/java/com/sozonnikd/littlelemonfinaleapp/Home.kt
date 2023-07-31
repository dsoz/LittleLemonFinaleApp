package com.sozonnikd.littlelemonfinaleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "restaurant logo",
            modifier = Modifier
                .width(150.dp)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.weight(0.17f))
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(60.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Profile.route)
                }
        )
    }
  //  sharedPreferences.edit().clear().commit()
}


@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}