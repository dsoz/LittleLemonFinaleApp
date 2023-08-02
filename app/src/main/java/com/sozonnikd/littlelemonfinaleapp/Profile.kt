package com.sozonnikd.littlelemonfinaleapp

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sozonnikd.littlelemonfinaleapp.ui.elements.RestaurantLogo
import com.sozonnikd.littlelemonfinaleapp.ui.elements.TextFieldInputAndDescription
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
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
    ) {
        RestaurantLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(id = R.string.personal_info),
            style = Typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp, bottom = 50.dp),
        )
        TextFieldInputAndDescription(
            inputValue = firstName,
            descriptionResource = R.string.first_name,
            readOnly = true)
        TextFieldInputAndDescription(
            inputValue = lastName,
            descriptionResource = R.string.last_name,
            readOnly = true)
        TextFieldInputAndDescription(
            inputValue = email,
            descriptionResource = R.string.email,
            readOnly = true)
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

