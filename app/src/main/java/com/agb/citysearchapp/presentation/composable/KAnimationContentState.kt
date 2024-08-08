package com.agb.citysearchapp.presentation.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize

/**
 * A composable function that provides animated content transitions based on a Boolean state.
 *
 * This function uses the `AnimatedContent` composable to smoothly animate between two content states,
 * which can be either the main content or a placeholder content.
 *
 * @param modifier A [Modifier] to be applied to the `Box` container. Default is `Modifier`.
 * @param state A Boolean value that determines which content to display. If `true`, the `content` composable
 *              is shown; otherwise, the `placeholderContent` composable is shown.
 * @param placeholderContent A composable function representing the placeholder content to be displayed when
 *                           `state` is `false`. Default is an empty composable.
 * @param content A composable function representing the main content to be displayed when `state` is `true`.
 */
@Composable
fun KAnimationContentState(
    modifier: Modifier = Modifier,
    state: Boolean,
    placeholderContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit
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
    ) { showContent ->
        Box(modifier = modifier) {
            if (showContent) {
                content()
            } else {
                placeholderContent()
            }
        }

    }
}