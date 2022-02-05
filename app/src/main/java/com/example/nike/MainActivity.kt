package com.example.nike

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nike.ui.theme.NikeTheme
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.ui.layout.ContentScale

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.nike.destinations.ContentScreenDestination
import com.example.nike.destinations.LogInScreenDestination
import com.example.nike.ui.theme.black
import com.example.nike.ui.theme.transparent
import com.example.nike.ui.theme.white
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            NikeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


//@Destination
//@Composable
//fun StartingScreen(
//    navigator: DestinationsNavigator,
//    id: Int
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Black)
//    ) {
//        Image(
//            painter = painterResource(R.drawable.nikelogo),
//            contentDescription = "logo",
//            modifier = Modifier.align(Alignment.Center)
//        )
//    }
//}

@Preview
@Destination(start = true)
@Composable
fun WelcomeScreen(
//    navigator: DestinationsNavigator
) {
    val scaffoldstate = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Image(
            painter = painterResource(R.drawable.welcomescreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            transparent,
                            black
                        )
                    )
                )
        ) {

        }
        Column(modifier = Modifier
            .padding(top = 50.dp, start = 40.dp, bottom = 40.dp, end = 40.dp)
            .wrapContentSize(align = Alignment.BottomStart)

        ) {
            Image(
                painter = painterResource(R.drawable.nikelogo),
                contentDescription = "logo",
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Text (
                text = "Приложение Nike\n" +
                        "Члены клуба Nike\n" +
                        "получают доступ к\n" +
                        "лучшим продуктам,\n" +
                        "источникам\n" +
                        "вдохновения\n" +
                        "и историям из\n" +
                        "мира спорта.",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = white,
            )

            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    shape = CircleShape,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 140.dp, minHeight = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = white
                    ),
                    onClick = {

                    }
                ){
                    Text("Давай с нами",
                        fontSize = 15.sp,
                        color = black,)
                }

                Button(
                    shape = CircleShape,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 140.dp, minHeight = 40.dp),
                    border = BorderStroke(2.dp, white),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = transparent
                    ),
                    onClick = {
//                        navigator.navigate(
//                            LogInScreenDestination()
//                        )
                    }
                ){
                    Text("Войти",

                        fontSize = 15.sp,
                        color = white)
                }
            }
        }
    }
}

@Destination
@Composable
fun LogInScreen(
   navigator: DestinationsNavigator
) {
    val scaffoldstate = rememberScaffoldState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    Box (
        modifier = Modifier
            .background(white)
            .fillMaxSize(),

    ) {
        Column(
            modifier = Modifier
                .padding(top = 200.dp, start = 40.dp, end = 40.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.nikelogoblack),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(50.dp)
            )

            Text(
                text = "ЕДИНЫЙ АККАУНТ ДЛЯ ЛЮБОЙ СРЕДЫ NIKE",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = black,
                textAlign = TextAlign.Center,
            )

            OutlinedTextField(                           //Email
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(height = 70.dp, width = 300.dp),
                value = email,
                onValueChange = {
                    newText -> email = newText
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                label = { Text("Адрес электронной почты") }
            )

            OutlinedTextField(                           //Password
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(height = 70.dp, width = 300.dp),
                value = password,
                onValueChange = {
                    newText -> password = newText
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                label = { Text("Пароль") }
            )

            Button (onClick = {
                 if(email.isNotEmpty() && password.isNotEmpty()) {
                    if(email == "admin" && password == "admin"){
                        navigator.navigate(ContentScreenDestination())
                    } else {
                        Toast.makeText(
                            context,
                            "Неправильный логин или пароль",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                 } else {
                     Toast.makeText(
                         context,
                         "Есть незаполненные поля",
                         Toast.LENGTH_SHORT
                     ).show()
                 }
            },
                modifier = Modifier
                    .padding(top = 25.dp)
                    .size(height = 50.dp, width = 300.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = black
                )
            ) {
                Text("Войти",
                    fontSize = 15.sp,
                    color = white)
            }

        }
    }
}

@Destination
@Composable
fun ContentScreen(
   navigator: DestinationsNavigator
) {

    LazyColumn(modifier = Modifier
        .background(white)
        .fillMaxSize()
        .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
            painter = painterResource(R.drawable.ic_jordan),
            contentDescription = "logo",
            modifier = Modifier
                .size(50.dp),
                )
        }
        item {
            Text(
                text = "КОЛЛЕКЦИЯ JORDAN PARIS SAINT-GERMAIN",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp, bottom = 15.dp)
            )
            Text(
                text = "Всегда роскошные, всегда легендарные",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Thin,
                fontSize = 12.sp,
                color = black,
                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(top = 30.dp)
            )
        }
        item {
            Button(
                shape = CircleShape,
                modifier = Modifier
                    .defaultMinSize(minWidth = 140.dp, minHeight = 40.dp)
                    .padding(top = 10.dp),
                border = BorderStroke(2.dp, black),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = black
                ),
                onClick = {}
            ){
                Text("Купить",
                    fontSize = 15.sp,
                    color = white)
            }
        }
        item {
            Image(
                painter = painterResource(R.drawable.image_10),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 40.dp, end = 40.dp),
                contentScale = ContentScale.Crop,
            )
            Box (contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.image_background),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    contentScale = ContentScale.Crop,
                )
                Image(
                    painter = painterResource(R.drawable.image_11),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .align(alignment = Alignment.Center),
                    )
            }
        }
    }
}








