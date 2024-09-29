package com.example.presenter.components.dialog

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        onDismissRequest = {
            onDismiss()
        },
        text = {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = errorMessage,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp),
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "OK",
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            }
        }
    )
}