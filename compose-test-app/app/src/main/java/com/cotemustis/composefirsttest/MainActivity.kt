package com.cotemustis.composefirsttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cotemustis.composefirsttest.ui.theme.ComposeFirstTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFirstTestTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF161D26))
                ) {
                    TweeterCard()
                    TweetDivider()
                    TweeterCard()
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TweeterCard() {
    var chatState by rememberSaveable { mutableStateOf(false) }
    var retweetState by rememberSaveable { mutableStateOf(false) }
    var tweetLikeState by rememberSaveable { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(55.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle(title = "Aris", modifier = Modifier.padding(end = 8.dp))
                DefaultSecondaryText(title = "@AristiDevs", modifier = Modifier.padding(end = 8.dp))
                DefaultSecondaryText(title = "4h", modifier = Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots),
                    contentDescription = "dots",
                    tint = Color.White
                )
            }
            TextBody(
                title = "Esto es un texto que es largo de prueba" +
                        "Esto es un texto que es largo de prueba" +
                        "Esto es un texto que es largo de prueba" +
                        "Esto es un texto que es largo de prueba",
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "image",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)), contentScale = ContentScale.Crop

            )
            Row(Modifier.padding(top = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "chat icon",
                            tint = Color.LightGray
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "selected chat icon",
                            tint = Color.LightGray
                        )
                    },
                    isSelected = chatState,
                ) {
                    chatState = !chatState
                }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "retweet icon",
                            tint = Color.LightGray
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "selected retweet icon",
                            tint = Color.Green
                        )
                    },
                    isSelected = retweetState,
                ) {
                    retweetState = !retweetState
                }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "like icon",
                            tint = Color.LightGray
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like_filled),
                            contentDescription = "selected like icon",
                            tint = Color.Red
                        )
                    },
                    isSelected = tweetLikeState,
                ) {
                    tweetLikeState = !tweetLikeState
                }
            }
        }
    }
}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unSelectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    var defaultValue = 1
    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unSelectedIcon()
        }
        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(title: String, modifier: Modifier) {
    Text(text = title, modifier = modifier, color = Color.White)
}

@Composable
fun TextTitle(title: String, modifier: Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultSecondaryText(title: String, modifier: Modifier) {
    Text(text = title, color = Color.LightGray, modifier = modifier)
}

@Composable
fun TweetDivider() {
    Divider(
        Modifier
            .padding(top = 4.dp)
            .height(1.dp)
            .fillMaxWidth(),
        color = Color.DarkGray
    )
}