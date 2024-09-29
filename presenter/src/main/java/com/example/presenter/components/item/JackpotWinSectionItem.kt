package com.example.presenter.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presenter.theme.DigitainTheme

@Composable
fun JackpotWinSectionItem(
    title: String,
    amount: String,
    date: String?,
    betId: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = amount,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormattedDateText(input = date)

            Text(
                text = betId,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun NoWinnerItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No Winners Yet",
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun JackpotWinSectionItemPreview() {
    DigitainTheme {
        Column {
            JackpotWinSectionItem(
                title = "Biggest Win",
                amount = "123456789",
                date = "8/25/2024 2:38:49 PM +03:00",
                betId = "Bet ID: 123456789"
            )

            Spacer(modifier = Modifier.height(16.dp))

            NoWinnerItem()
        }
    }
}