package com.sozonnikd.littlelemonfinaleapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography
import com.sozonnikd.littlelemonfinaleapp.ui.theme.YellowMain

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "restaurant logo",
            modifier = Modifier
                .width(150.dp)
                .padding(10.dp)
        )
        Text(
            text = stringResource(id = R.string.personal_info),
            style = Typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, bottom = 50.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = stringResource(id = R.string.first_name),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 5.dp)
        )
        Text(
            text = firstName,
            textAlign = TextAlign.Start,
            style = Typography.h5,
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(10.dp)
                .fillMaxWidth(),
        )
        Text(
            text = stringResource(id = R.string.last_name),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp, bottom = 5.dp)
        )
        Text(
            text = lastName,
            textAlign = TextAlign.Start,
            style = Typography.h5,
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(10.dp)
                .fillMaxWidth(),
        )
        Text(
            text = stringResource(id = R.string.email),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp, bottom = 5.dp)
        )
        Text(
            text = email,
            textAlign = TextAlign.Start,
            style = Typography.h5,
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(10.dp)
                .fillMaxWidth(),
        )

        Button(
            onClick = {
                sharedPreferences.edit().clear().commit()
                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = YellowMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(
                text = stringResource(id = R.string.log_out),
                style = Typography.button,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(rememberNavController())
}