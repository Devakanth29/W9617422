package uk.ac.tees.mad.w9617422.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import uk.ac.tees.mad.w9617422.R
import uk.ac.tees.mad.w9617422.screens.destinations.WatchListDestination
import uk.ac.tees.mad.w9617422.sharedComposables.BackButton
import uk.ac.tees.mad.w9617422.sharedComposables.CustomSwitch
import uk.ac.tees.mad.w9617422.ui.theme.AppOnPrimaryColor
import uk.ac.tees.mad.w9617422.ui.theme.AppPrimaryColor
import uk.ac.tees.mad.w9617422.ui.theme.ButtonColor
import uk.ac.tees.mad.w9617422.viewmodel.PrefsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import uk.ac.tees.mad.w9617422.screens.destinations.HomeDestination

//import uk.ac.tees.mad.w9617422.screens.destinations.EditScreenDestination


@Destination
@Composable
fun Profile(
    navigator: DestinationsNavigator,
    prefsViewModel: PrefsViewModel = hiltViewModel()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    ) {
        val (
            backButton,
            editButton,
            profileHeading,
            userName,
            topBgImage,
            profilePhoto,
            imageBoarder,
            translucentBr,
            btnWatchList,
            settingsBox,
            appVersion
        ) = createRefs()

        BackButton(
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(parent.start, margin = 10.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }) {
            navigator.navigateUp()
        }

        FloatingActionButton(
            modifier = Modifier
                .size(42.dp)
                .constrainAs(editButton) {
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                },
            backgroundColor = ButtonColor,
            contentColor = AppOnPrimaryColor,
            onClick = { }) {
            Icon(imageVector = Icons.Rounded.Edit, contentDescription = "edit profile")
        }

        Text(
            modifier = Modifier.constrainAs(profileHeading) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(backButton.top)
                bottom.linkTo(backButton.bottom)
            },
            text = "Profile",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppOnPrimaryColor
        )

        Image(
            painter = painterResource(id = R.drawable.popcorn),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.27F)
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .constrainAs(topBgImage) {
                    top.linkTo(backButton.bottom, margin = 16.dp)
                },
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0XFF180E36).copy(alpha = 0.5F),
                            Color(0XFF180E36).copy(alpha = 0.75F),
                            Color(0XFF180E36).copy(alpha = 1F)
                        ),
                        startY = 0F
                    )
                )
                .constrainAs(translucentBr) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(topBgImage.bottom)
                }
        )


        Box(
            modifier = Modifier
                .size(83.5.dp)
                .clip(CircleShape)
                .background(AppPrimaryColor)
                .constrainAs(imageBoarder) {
                    top.linkTo(topBgImage.bottom)
                    start.linkTo(topBgImage.start, margin = 26.dp)
                    bottom.linkTo(topBgImage.bottom)
                }
        )

        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
        val pickImage = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = uri
            }
        }

        CoilImage(
            imageModel = if (selectedImageUri!=null) selectedImageUri else R.drawable.ic_user,
            previewPlaceholder = R.drawable.ic_user,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000),
            shimmerParams = ShimmerParams(
                baseColor = AppOnPrimaryColor,
                highlightColor = Color.LightGray,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .constrainAs(profilePhoto) {
                    top.linkTo(topBgImage.bottom)
                    start.linkTo(topBgImage.start, margin = 28.dp)
                    bottom.linkTo(topBgImage.bottom)
                }
                .clickable {
                    // Launch the image picker
                    pickImage.launch("image/*")
                },
            contentDescription = null
        )

        fun userName(name: String): String {
            return if (name.length <= 16) name else {
                name.removeRange(15..name.lastIndex) + "..."
            }
        }

        Text(
            text = userName("WatchWave"),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = AppOnPrimaryColor,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(profilePhoto.bottom, margin = 4.dp)
                start.linkTo(profilePhoto.start)
                end.linkTo(profilePhoto.end)
            }
        )

        Button(
            modifier = Modifier
                .constrainAs(btnWatchList) {
                    end.linkTo(parent.end, margin = 24.dp)
                    top.linkTo(topBgImage.bottom)
                    bottom.linkTo(topBgImage.bottom)
                },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF27A2A2),
                contentColor = AppOnPrimaryColor
            ),
            onClick = {
                navigator.navigate(WatchListDestination)
            }
        ) {
            Text(text = "My List")
        }



        Column(
            modifier = Modifier.constrainAs(appVersion) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 24.dp)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = "Test",
                    singleLine = true,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = "Name")},
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {}
                    )
                )
                OutlinedTextField(
                    value = "test@gmail.com",
                    singleLine = true,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = "Email")},
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {}
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF27A2A2),
                        contentColor = AppOnPrimaryColor),
                    onClick = {
                        navigator.navigate(HomeDestination)
                    },
                ) {
                    Text(text = "Save")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.watchwavelogo),
                modifier = Modifier.widthIn(max = 100.dp),
                alpha = 0.78F,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))

        }


    }
}
