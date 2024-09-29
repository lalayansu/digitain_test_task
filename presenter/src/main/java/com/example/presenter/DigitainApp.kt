package com.example.presenter

import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presenter.extensions.paddingValuesVectorConverter
import com.example.presenter.navigation.Destinations.JACKPOT_SCREEN_DESTINATION
import com.example.presenter.theme.DigitainTheme
import com.example.presenter.ui.jackpot.JackpotScreen
import com.example.presenter.ui.jackpot.JackpotViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DigitainApp(
    startRoute: String
) {
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = rememberTopAppBarState())
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route.orEmpty()

    DigitainTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
            content = { paddingValues ->
                val animatedPaddingValues = animateValueAsState(
                    targetValue = when (currentRoute) {
                        JACKPOT_SCREEN_DESTINATION -> PaddingValues(0.dp)
                        else -> paddingValues
                    },
                    typeConverter = paddingValuesVectorConverter(
                        layoutDirection = LocalLayoutDirection.current
                    ),
                    animationSpec = tween(durationMillis = 300),
                    label = ""
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .navigationBarsPadding()
                        .statusBarsPadding()
                        .padding(paddingValues = animatedPaddingValues.value)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startRoute,
                    ) {
                        composable(JACKPOT_SCREEN_DESTINATION) {
                            JackpotScreen(
                                viewModel = hiltViewModel<JackpotViewModel>()
                            )
                        }
                    }
                }
            }
        )
    }
}


@Preview
@Composable
fun DigitainAppPreview() {
    DigitainTheme {
        DigitainApp(
            startRoute = JACKPOT_SCREEN_DESTINATION,
        )
    }
}