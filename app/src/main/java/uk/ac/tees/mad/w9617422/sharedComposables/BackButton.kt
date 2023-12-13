package uk.ac.tees.mad.w9617422.sharedComposables

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.w9617422.ui.theme.AppOnPrimaryColor
import uk.ac.tees.mad.w9617422.ui.theme.ButtonColor

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier.size(42.dp),
        backgroundColor = ButtonColor,
        contentColor = AppOnPrimaryColor,
        onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back icon",
        )
    }
}
