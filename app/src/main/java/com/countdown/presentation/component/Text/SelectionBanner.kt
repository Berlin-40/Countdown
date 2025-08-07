package com.countdown.presentation.component.Text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countdown.ui.theme.RedDark
import com.countdown.ui.theme.White

@Composable
fun SelectionBanner(
    text: String,
    onSelectAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = modifier
            .clickable(onClick = onSelectAll)
            .clip(RoundedCornerShape(50.dp))
            .background(White)
            .padding(8.dp),
        fontSize = 10.sp
    )
}

@Preview
@Composable
fun SelectionBannerPreview() {
    SelectionBanner(
        text = "Tout sélectionner",
        onSelectAll = {}
    )
}

