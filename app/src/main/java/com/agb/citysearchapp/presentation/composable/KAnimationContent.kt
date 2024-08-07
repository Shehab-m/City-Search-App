package com.agb.citysearchapp.presentation.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Displays animated content based on a loading state with support for top bar and error handling.
 *
 * This composable uses AnimatedContent to switch between loading and main content with animations.
 * It supports displaying a top bar, loading content, and an error state with a try-again button.
 *
 * @param modifier Modifier to apply to the root layout.
 * @param state The loading state; true for loading, false for main content.
 * @param topBar Composable for the top bar.
 * @param loadingContent Composable to display while loading.
 * @param isError Indicates if an error occurred; displays error content if true.
 * @param onClickTryAgain Callback for the try-again button.
 * @param backgroundColor Background color for the root layout.
 * @param content Main content to display when not loading or in an error state.
 */
@Composable
fun KAnimationContent(
    modifier: Modifier = Modifier,
    state: Boolean,
    topBar: @Composable () -> Unit = {},
    loadingContent: @Composable () -> Unit,
    isError: Boolean = false,
    onClickTryAgain: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(animationSpec = tween(150, 150)) togetherWith
                    fadeOut(animationSpec = tween(150)) using
                    SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                IntSize(targetSize.width, initialSize.height) at 150
                                durationMillis = 300
                            }
                        } else {
                            keyframes {
                                IntSize(initialSize.width, targetSize.height) at 150
                                durationMillis = 300
                            }
                        }
                    }
        }, label = "Content"
    ) { isLoading ->
        Column(
            modifier.fillMaxSize().background(backgroundColor)
        ) {
            topBar()
            if (isError) {
                KErrorLoading(state = isError, onClickTryAgain = onClickTryAgain)
            } else {
                if (isLoading) {
                    loadingContent()
                } else {
                    content()
                }
            }

        }
    }
}