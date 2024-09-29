package com.example.presenter.components.brush

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffectForHorizontalItems() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(count = 2) {
            Box(
                modifier = Modifier
                    .fillParentMaxWidth(fraction = 0.9f)
                    .height(56.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(shimmerBrushForHorizontalItems())
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerEffectForHorizontalItemsPreview() {
    ShimmerEffectForHorizontalItems()
}