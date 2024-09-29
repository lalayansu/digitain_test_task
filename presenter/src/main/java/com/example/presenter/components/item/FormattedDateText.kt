package com.example.presenter.components.item

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.example.presenter.theme.DigitainTheme
import com.example.presenter.utils.parseAndFormatDate

@Composable
fun FormattedDateText(
    modifier: Modifier = Modifier,
    input: String?
) {
    val formattedDate = input?.parseAndFormatDate()
    val parts = formattedDate?.split(" | ")
    val annotatedString = buildAnnotatedString {
        append(text = parts?.get(0).orEmpty())

        append(
            text = AnnotatedString(
                text = " | ",
                spanStyle = SpanStyle(color = MaterialTheme.colorScheme.tertiary)
            )
        )

        append(text = parts?.get(1).orEmpty())
    }

    Text(
        modifier = modifier,
        text = annotatedString,
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        style = MaterialTheme.typography.bodySmall,
    )
}

@Preview
@Composable
fun FormattedDateTextPreview() {
    DigitainTheme {
        FormattedDateText(input = "8/25/2024 2:38:49 PM +03:00")
    }
}