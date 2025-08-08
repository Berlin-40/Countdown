package com.countdown.presentation.component.topbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.countdown.R
import com.countdown.ui.theme.Black
import com.countdown.ui.theme.RedPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCountdownList(
    title: String = "",
    onNavigation: @Composable () -> Unit = {},
    onAction: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
){
    TopAppBar(
        navigationIcon = {
            onNavigation()
        },
        actions = {
            onAction()
        },
        title = {
            Text(text = title)
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RedPrimary,
        ),
        windowInsets = WindowInsets(0)
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarCountdownListPreview(){
    TopBarCountdownList(
        title = "Banner1"
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarCountdownListWithActionPreview(){
    TopBarCountdownList(
        title = "Banner1",
        onAction = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Black
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun TopBarCountdownListWithNavigationPreview(){
    TopBarCountdownList(
        title = "Banner",
        onNavigation = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Black
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarCountdownListWithNavigationPreviewB(){
    TopBarCountdownList(
        title = "Banner",
        onNavigation = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Back",
                    tint = Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}
