package uk.ac.tees.mad.w9617422.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uk.ac.tees.mad.w9617422.ui.theme.WatchWaveTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchWaveTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
