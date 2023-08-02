package com.sozonnikd.littlelemonfinaleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.sozonnikd.littlelemonfinaleapp.ui.elements.CategoryButton
import com.sozonnikd.littlelemonfinaleapp.ui.elements.RestaurantLogo
import com.sozonnikd.littlelemonfinaleapp.ui.theme.GreyHighlight
import com.sozonnikd.littlelemonfinaleapp.ui.theme.GreyMain
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography
import com.sozonnikd.littlelemonfinaleapp.ui.theme.YellowMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
    val databaseMenuItems = database.menuItemDao().getAll().observeAsState(initial = emptyList()).value
    val productListState: MutableStateFlow<List<MenuItemRoom>> = MutableStateFlow(databaseMenuItems)

    ConstraintLayout (modifier = Modifier.fillMaxWidth()){
        val (restNameText, cityText, descriptionText,
            photo, searchBox, logoImage, profileImage,
            backgroundBox, menuList, divider, orderText, row) = createRefs()

        var searchField by remember { mutableStateOf("") }

        RestaurantLogo(modifier = Modifier.constrainAs(logoImage) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)
                .constrainAs(profileImage) {
                    end.linkTo(parent.end)
                }
                .clickable {
                    navController.navigate(Profile.route)
                }
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(GreyMain)
            .constrainAs(backgroundBox) {
                top.linkTo(logoImage.bottom, 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = stringResource(id = R.string.restaurant_name),
            style = Typography.h5,
            fontSize = 60.sp,
            color = YellowMain,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(restNameText) {
                    top.linkTo(backgroundBox.top)
                }
        )
        Text(
            text = stringResource(id = R.string.city),
            style = Typography.h5,
            fontSize = 40.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(cityText) {
                    top.linkTo(restNameText.top, 50.dp)
                }
        )
        Text(
            text = stringResource(id = R.string.short_description),
            style = Typography.body1,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .width(200.dp)
                .padding(start = 10.dp)
                .constrainAs(descriptionText) {
                    top.linkTo(cityText.top, 50.dp)
                    start.linkTo(backgroundBox.start)
                    end.linkTo(photo.start, 5.dp)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.hero_image),
            contentDescription = "Hero image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(150.dp)
                .constrainAs(photo) {
                    end.linkTo(backgroundBox.end, 10.dp)
                    top.linkTo(backgroundBox.top, 70.dp)
                }
        )
        OutlinedTextField(
            value = searchField,
            onValueChange = {input -> searchField = input},
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            placeholder = {
                Text(text = stringResource(id = R.string.search_placeholder), textAlign = TextAlign.Center)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "")
                          },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = GreyHighlight
            ),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .constrainAs(searchBox) {
                    top.linkTo(photo.bottom, 10.dp)
                }
        )
        Text(
            text = stringResource(id = R.string.order_text),
            style = Typography.h5,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(orderText) {
                    top.linkTo(backgroundBox.bottom, 5.dp)
                }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .constrainAs(row) {
                    top.linkTo(orderText.bottom, 5.dp)
                }
        ) {
            CategoryButton(
                onClick = { productListState.update { databaseMenuItems.filter { it.category == "starters" } } },
                categoryName = stringResource(id = R.string.starters)
            )
            CategoryButton(
                onClick = { productListState.update { databaseMenuItems.filter { it.category == "mains" } } },
                categoryName = stringResource(id = R.string.mains)
            )
            CategoryButton(
                onClick = { productListState.update { databaseMenuItems.filter { it.category == "desserts" } } },
                categoryName = stringResource(id = R.string.deserts)
            )
            CategoryButton(
                onClick = { productListState.update { databaseMenuItems.filter { it.category == "drinks" } } },
                categoryName = stringResource(id = R.string.drinks)
            )
        }
        Divider(
            color = GreyHighlight,
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .constrainAs(divider) {
                    top.linkTo(row.bottom, 10.dp)
                }
        )

        if (searchField.isNotBlank()){
            productListState.update { databaseMenuItems.filter { it.title.contains(searchField, ignoreCase = true)} }
        }
        MenuItems(
            items = productListState.collectAsState().value,
            modifier = Modifier
                .constrainAs(menuList){
                    top.linkTo(divider.bottom)
                }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}
