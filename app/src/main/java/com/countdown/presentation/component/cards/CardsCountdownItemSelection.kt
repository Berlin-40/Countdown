package com.countdown.presentation.component.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.countdown.ui.theme.RedDark
import com.countdown.ui.theme.RedPrimary

@Composable
fun CardsCountdownItemSelection(
    id: Int,
    title: String,
    duration: String,
    isSelected: Boolean,
    toggleSelection: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val isSelectedItem  = isSelected
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{toggleSelection()},
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(!isSelectedItem) RedPrimary else RedDark,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Checkbox(
                checked = isSelectedItem,
                onCheckedChange = { toggleSelection() }
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = duration,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardsCountdownItemSelectionPreview(){

    CardsCountdownItemSelection(1,"Birthday","10 days", isSelected = false)
}