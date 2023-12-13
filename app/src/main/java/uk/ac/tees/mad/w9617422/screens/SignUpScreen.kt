package uk.ac.tees.mad.w9617422.screens

import java.util.*
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uk.ac.tees.mad.w9617422.R
import uk.ac.tees.mad.w9617422.data.UserData
import uk.ac.tees.mad.w9617422.screens.destinations.HomeDestination
import uk.ac.tees.mad.w9617422.screens.destinations.loginScreenDestination
import uk.ac.tees.mad.w9617422.ui.theme.AppPrimaryColor


@Destination
@Composable
fun SignUpScreen(
    navigator: DestinationsNavigator,
) {


    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    ) {
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.watchwavelogo),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
                    .padding(40.dp)
            )

            Text(
                text = "Welcome",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Create your account",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Username TextInputLayout
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = {  Text("Email", color = Color.White)},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Email",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    cursorColor = Color.White
                ),
            )

            // Password TextInputLayout
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {  Text("Password", color = Color.White)},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "Password",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    cursorColor = Color.White
                )
            )

            // Button
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                        navigator.navigate(HomeDestination)
                    } else {
                        Toast.makeText(context, "Please fill Username or password ", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text("SignUp", color = Color(android.graphics.Color.parseColor("#b20000")))
            }

            // Show error message if any
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Row with Sign Up text
            Row(
                modifier = Modifier.fillMaxWidth().clickable {
                    navigator.navigate(loginScreenDestination)
                },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an Account? Login",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Sign Up",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}











