package com.countdown.presentation.component.scrollList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CircularScrollList(
    range: IntRange = 0..59,
    modifier: Modifier = Modifier,
    onNumberSelected: (Int) -> Unit,
    ) {
    val numbers = range.toList()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 0)

    // Récupère l’élément centré
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect {
                val index = listState.firstVisibleItemIndex
                val selectedNumber = numbers.getOrNull(index) ?: 1
                onNumberSelected(selectedNumber)
            }
    }
    LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 30.dp), // pour centrer l'élément
            verticalArrangement = Arrangement.Center,
            modifier = modifier.height(100.dp)
        ) {
            items(numbers.size) { index ->
                val number = numbers[index]
                val isSelected = index == listState.firstVisibleItemIndex

                Text(
                    text = number.toString(),
                    fontSize = if (isSelected) 38.sp else 25.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Black else Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
}
