package com.sozonnikd.littlelemonfinaleapp


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sozonnikd.littlelemonfinaleapp.ui.elements.RestaurantLogo
import com.sozonnikd.littlelemonfinaleapp.ui.elements.TextFieldInputAndDescription
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography
import com.sozonnikd.littlelemonfinaleapp.ui.theme.YellowMain


@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
    ) {
        RestaurantLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(id = R.string.greetings_onboarding),
            style = Typography.h5,
            color = Color.White,
            modifier = Modifier
                .background(colorResource(id = R.color.grey_main), RectangleShape)
                .padding(50.dp, 30.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.personal_info),
            style = Typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),

        )
        TextFieldInputAndDescription(
            inputValue = firstName,
            descriptionResource = R.string.first_name,
            onValueChange = {input -> firstName = input}
        )
        TextFieldInputAndDescription(
            inputValue = lastName,
            descriptionResource = R.string.last_name,
            onValueChange = {input -> lastName = input}
        )
        TextFieldInputAndDescription(
            inputValue = email,
            descriptionResource = R.string.email,
            onValueChange = {input -> email = input}
        )
        Button(
            onClick = {
                      if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                          makeToast(context, "Registration unsuccessful. Please enter all data.")
                      } else{
                          makeToast(context, "Registration successful!")
                          saveData(context = context, firstName, lastName, email)
                          navController.navigate(Home.route)
                      }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = YellowMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = Typography.button,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(rememberNavController())
}

fun makeToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun saveData(context: Context, firstName: String, lastName: String, email: String){
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.edit()
        .putString("firstName", firstName)
        .putString("lastName", lastName)
        .putString("email", email)
        .commit()
}