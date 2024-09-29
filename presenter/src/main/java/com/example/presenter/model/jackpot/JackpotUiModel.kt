package com.example.presenter.model.jackpot

import com.example.digitain_test_task.presenter.R
import com.example.domain.model.jackpot.Jackpot

data class JackpotUiModel(
    val digitsAfterPoint: Int? = null,
    val currency: String? = null,
    val currencySymbol: String? = null,
    val jackpotWidgetUiModel: JackpotWidgetUiModel? = null,
    val cardSuitUiModelList: List<CardSuitUiModel?>? = null
)

fun Jackpot.toUiModel(): JackpotUiModel {
    val cardSuitConfigList = listOf(
        CardSuiteConfigs.DIAMOND,
        CardSuiteConfigs.GOLD,
        CardSuiteConfigs.SILVER,
        CardSuiteConfigs.BRONZE
    )

    val cardSuitUiModelList = cardSuitList?.mapIndexed { index, cardSuit ->
        val cardSuitConfig = cardSuitConfigList.getOrNull(index)

        cardSuit?.toUiModel(
            backgroundRes = cardSuitConfig?.suitBackground ?: R.drawable.bg_diamond,
            iconRes = cardSuitConfig?.suitIcon ?: R.drawable.ic_diamond,
            name = cardSuitConfig?.suitName ?: "Diamond",
            digitCount = if (digitsAfterPoint == null || digitsAfterPoint == 0) 2 else digitsAfterPoint
                ?: 2
        )
    }

    return JackpotUiModel(
        digitsAfterPoint = digitsAfterPoint,
        currency = currency,
        currencySymbol = currencySymbol,
        jackpotWidgetUiModel = jackpotWidget?.toUiModel(),
        cardSuitUiModelList = cardSuitUiModelList
    )
}

enum class CardSuiteConfigs(
    val suitName: String,
    val suitIcon: Int,
    val suitBackground: Int
) {
    DIAMOND(
        suitName = "Diamond",
        suitIcon = R.drawable.ic_diamond,
        suitBackground = R.drawable.bg_diamond
    ),
    GOLD(
        suitName = "Gold",
        suitIcon = R.drawable.ic_gold,
        suitBackground = R.drawable.bg_gold
    ),
    SILVER(
        suitName = "Silver",
        suitIcon = R.drawable.ic_silver,
        suitBackground = R.drawable.bg_silver
    ),
    BRONZE(
        suitName = "Bronze",
        suitIcon = R.drawable.ic_bronze,
        suitBackground = R.drawable.bg_bronze
    )
}