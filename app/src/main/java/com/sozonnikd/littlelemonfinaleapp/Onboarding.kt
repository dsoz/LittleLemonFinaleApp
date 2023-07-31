package com.sozonnikd.littlelemonfinaleapp


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
        modifier = Modifier.fillMaxSize(),
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
                .padding(10.dp, 50.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = stringResource(id = R.string.first_name),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 5.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = {input -> firstName = input},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
            shape = RoundedCornerShape(8.dp),
        )
        Text(
            text = stringResource(id = R.string.last_name),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 5.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = {input -> lastName = input},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
            shape = RoundedCornerShape(8.dp),
        )
        Text(
            text = stringResource(id = R.string.email),
            style = Typography.h6,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 5.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {input -> email = input},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
            shape = RoundedCornerShape(8.dp),
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
                .padding(start = 10.dp, end = 10.dp, top = 40.dp)
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
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
}

fun saveData(context: Context, firstName: String, lastName: String, email: String){
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPreferences.edit()
        .putString("firstName", firstName)
        .putString("lastName", lastName)
        .putString("email", email)
        .commit()
}


@Composable
fun TextFieldInputAndDescription(inputValue: String,
                                 descriptionResource: Int,
                                 onValueChange: (String) -> Unit = {},
                                 readOnly: Boolean = false, ) {
    Text(
        text = stringResource(descriptionResource),
        style = Typography.h6,
        modifier = Modifier
            //.align(Alignment.Start)
            .padding(start = 10.dp, bottom = 5.dp))
    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChange,
        readOnly = readOnly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
        shape = RoundedCornerShape(8.dp),
    )
}