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
import androidx.compose.material.OutlinedTextField
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
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "restaurant logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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
        )
        TextFieldAndDescription(
            inputValue = firstName,
            descriptionResource = R.string.first_name
        )
        TextFieldAndDescription(
            inputValue = lastName,
            descriptionResource = R.string.last_name
        )
        TextFieldAndDescription(
            inputValue = email,
            descriptionResource = R.string.email)
        Button(
            onClick = {
                sharedPreferences.edit().clear().commit()
                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = YellowMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
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

@Composable
fun TextFieldAndDescription(inputValue: String, descriptionResource: Int){
    Text(
        text = stringResource(id = descriptionResource),
        style = Typography.h6,
        modifier = Modifier
            .padding(bottom = 5.dp)
    )
    OutlinedTextField(
        value = inputValue,
        onValueChange = {},
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        shape = RoundedCornerShape(8.dp),
    )
}

