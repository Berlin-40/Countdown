package com.countdown.presentation.minuteur

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.countdown.presentation.component.animation.MarmiteAnimation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.countdown.presentation.component.box.BoxMinuteur
import com.countdown.presentation.component.cards.CardMinuteur
import com.countdown.presentation.component.topbar.TopBarCountdownListPreview
import com.countdown.ui.theme.Black
import com.countdown.ui.theme.White

@Composable
fun MinuteurUi(
    viewModel: MinuteurViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val minuteurState by viewModel.minuteurState.collectAsState()
    val context = LocalContext.current

    // Lancer un effet si un message apparaît
    LaunchedEffect(minuteurState.started ,minuteurState.errorMessage, minuteurState.infoMessage) {
        viewModel.run()
        minuteurState.errorMessage?.let {
            Toast.makeText(context, minuteurState.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearMessages()
        }
        minuteurState.infoMessage?.let {
            Toast.makeText(context, minuteurState.infoMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearMessages()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        CardMinuteur(
            started = minuteurState.launched,
            onAction = viewModel::onAction,
            minute = minuteurState.minute.toString(),
            seconde = minuteurState.seconde.toString(),
        )
        if(minuteurState.launched){
            IconButton(
                onClick = {
                    viewModel.onAction(MinuteurAction.Reset)
                },
                modifier = Modifier.offset(y = 350.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Réinitialiser",
                    tint = Black
                )
            }
        }
        MarmiteAnimation(
            start = minuteurState.started,
            duration = minuteurState.minute*60 + minuteurState.seconde,
            onAction = viewModel::onAction
        )

    }
}
@Preview(showBackground = true)
@Composable
fun MinuteurUiPreview(){
    MinuteurUi(navController = NavController(LocalContext.current))
}