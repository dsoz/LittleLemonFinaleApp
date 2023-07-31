package com.sozonnikd.littlelemonfinaleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sozonnikd.littlelemonfinaleapp.ui.theme.GreyHighlight
import com.sozonnikd.littlelemonfinaleapp.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .height(350.dp)
            .padding(top = 5.dp, start = 10.dp, end = 10.dp),
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (itemTitleText, itemDescriptionText, itemImage, itemPriceText, divider) = createRefs()

                    Text(
                        text = menuItem.title,
                        style = Typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(itemTitleText) {
                                top.linkTo(parent.top)
                            }
                    )
                    Text(
                        text = menuItem.description,
                        style = Typography.h6,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 5.dp)
                            .width(220.dp)
                            .constrainAs(itemDescriptionText) {
                                top.linkTo(itemTitleText.bottom)

                            }
                    )
                    GlideImage(
                        model = menuItem.image,
                        contentDescription = menuItem.title,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.BottomEnd,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 5.dp)
                            .constrainAs(itemImage) {
                                //  start.linkTo(itemDescriptionText.end, 5.dp)
                                end.linkTo(parent.end, 5.dp)
                            },
                    )
                    Text(
                        text = "$%.2f".format(menuItem.price),
                        modifier = Modifier.constrainAs(itemPriceText) {
                            top.linkTo(itemDescriptionText.bottom)
                        }
                    )
                    Divider(
                        color = GreyHighlight,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .constrainAs(divider) {
                                top.linkTo(itemPriceText.bottom)
                            }
                    )
                }
            }
        )
    }
}
