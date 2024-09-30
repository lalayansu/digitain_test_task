package com.example.presenter.ui.jackpot

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitain_test_task.presenter.R
import com.example.presenter.components.brush.ShimmerEffectForHorizontalItems
import com.example.presenter.components.dialog.ErrorDialog
import com.example.presenter.components.item.JackpotItem
import com.example.presenter.model.jackpot.CardSuitUiModel
import com.example.presenter.model.jackpot.JackpotUiModel
import com.example.presenter.model.jackpot.TopMonthlyWinnerUiModel
import com.example.presenter.theme.DigitainTheme
import com.example.presenter.ui.jackpot.bottomsheet.JackpotBottomSheet
import com.example.presenter.utils.rememberFlowWithLifecycle
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JackpotScreen(
    viewModel: JackpotViewModel,
) {
    val state by viewModel.state.collectAsState()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    var isErrorDialogVisible by rememberSaveable { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is JackpotContract.JackpotViewEffect.NavigateToJackpotDetails -> {
                    isBottomSheetVisible = true
                }

                is JackpotContract.JackpotViewEffect.ShowError -> isErrorDialogVisible = true
            }
        }
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            content = {
                if (isBottomSheetVisible) {
                    JackpotBottomSheet(
                        cardSuitUiModelList = state.jackpotUiModel?.cardSuitUiModelList
                    )
                }
            },
            onDismissRequest = {
                isBottomSheetVisible = false
            },
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )
    }

    if (isErrorDialogVisible) {
        ErrorDialog(
            errorMessage = state.errorMessage,
            onDismiss = {
                isErrorDialogVisible = false
                viewModel.dismissErrorDialog()
            }
        )
    }

    JackpotScreenContent(
        modifier = Modifier,
        isLoading = state.isLoading,
        jackpotUiModel = state.jackpotUiModel,
        onJackpotClick = {
            viewModel.onJackpotClick()
        }
    )
}

@Composable
fun JackpotScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    jackpotUiModel: JackpotUiModel? = null,
    onJackpotClick: () -> Unit = {}
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            ShimmerEffectForHorizontalItems()
        }

        return
    }

    val cardSuitList = remember(jackpotUiModel) {
        jackpotUiModel?.cardSuitUiModelList.orEmpty()
    }

    AnimatedVisibility(
        visible = cardSuitList.isEmpty(),
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { -it }
        ) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No card suits available",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    AnimatedVisibility(
        visible = cardSuitList.isNotEmpty(),
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { -it }
        ) + fadeOut()
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            itemsIndexed(
                items = cardSuitList,
                key = { _, cardSuit ->
                    cardSuit.toString()
                }
            ) { _, cardSuit ->
                JackpotItem(
                    modifier = Modifier
                        .fillParentMaxWidth(fraction = 0.9f),
                    iconRes = cardSuit?.iconRes ?: R.drawable.ic_diamond,
                    backgroundRes = cardSuit?.backgroundRes ?: R.drawable.bg_diamond,
                    onClick = {
                        onJackpotClick.invoke()
                    },
                    startIntegerPart = cardSuit?.startIntegerDigits.orEmpty(),
                    endIntegerPart = cardSuit?.endIntegerDigits.orEmpty(),
                    startFractionalPart = cardSuit?.startFractionalDigits.orEmpty(),
                    endFractionalPart = cardSuit?.endFractionalDigits.orEmpty()
                )
            }
        }
    }
}

@Preview
@Composable
fun JackpotScreenPreview() {
    DigitainTheme {
        Column {
            JackpotScreenContent(
                jackpotUiModel = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            JackpotScreenContent(
                jackpotUiModel = JackpotUiModel(
                    cardSuitUiModelList = listOf(
                        CardSuitUiModel(
                            current = BigDecimal("12345.67"),
                            previous = BigDecimal("12000.55"),
                            largestWin = BigDecimal("50000.89"),
                            largestWinString = "50,000.89",
                            largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                            largestWinUser = "100500",
                            lastWin = BigDecimal("45000.00"),
                            lastWinString = "45,000.00",
                            lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                            lastWinUser = "100500",
                            topMonthlyWinnerUiModelList = listOf(
                                TopMonthlyWinnerUiModel(
                                    winAmount = BigDecimal("10000.50"),
                                    winDate = "2024-09-20",
                                    winUser = "John Doe"
                                ),
                                TopMonthlyWinnerUiModel(
                                    winAmount = BigDecimal("10000.50"),
                                    winDate = "2024-09-20",
                                    winUser = "John Doe"
                                )
                            ),
                            topYearlyWinners = listOf("Alice", "Bob", "Charlie"),
                            wins = 20,
                            backgroundRes = R.drawable.bg_diamond,
                            iconRes = R.drawable.ic_diamond,
                            name = "Diamond",
                            startIntegerDigits = listOf(1, 2, 3, 4, 5),
                            startFractionalDigits = listOf(6, 7),
                            endIntegerDigits = listOf(1, 2, 3, 4, 6),
                            endFractionalDigits = listOf(7, 9)
                        ),
                        CardSuitUiModel(
                            current = BigDecimal("12345.67"),
                            previous = BigDecimal("12000.55"),
                            largestWin = BigDecimal("50000.89"),
                            largestWinString = "50,000.89",
                            largestWinDate = "8/25/2024 2:38:49 PM +03:00",
                            largestWinUser = "100500",
                            lastWin = BigDecimal("45000.00"),
                            lastWinString = "45,000.00",
                            lastWinDate = "8/25/2024 2:38:49 PM +03:00",
                            lastWinUser = "100500",
                            topMonthlyWinnerUiModelList = listOf(
                                TopMonthlyWinnerUiModel(
                                    winAmount = BigDecimal("10000.50"),
                                    winDate = "2024-09-20",
                                    winUser = "John Doe"
                                ),
                                TopMonthlyWinnerUiModel(
                                    winAmount = BigDecimal("10000.50"),
                                    winDate = "2024-09-20",
                                    winUser = "John Doe"
                                )
                            ),
                            topYearlyWinners = listOf("Alice", "Bob", "Charlie"),
                            wins = 20,
                            backgroundRes = R.drawable.bg_gold,
                            iconRes = R.drawable.ic_gold,
                            name = "Gold",
                            startIntegerDigits = listOf(1, 2, 3, 4, 5),
                            startFractionalDigits = listOf(6, 7),
                            endIntegerDigits = listOf(1, 2, 3, 4, 6),
                            endFractionalDigits = listOf(7, 9)
                        )
                    )
                )
            )
        }
    }
}