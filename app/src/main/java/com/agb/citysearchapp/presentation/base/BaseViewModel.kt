package com.agb.citysearchapp.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

abstract class BaseViewModel<S, E>(initialState: S) : ViewModel(), BaseInteractionListener {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<E?>()
    val effect = _effect.asSharedFlow().mapNotNull { it }

    /**
     * Executes a suspend function and handles success and error functions.
     *
     * It handles the result using the provided onSuccess and onError callbacks.
     *
     * @param function The suspend function to execute.
     * @param onSuccess Callback to handle the result of the function on success.
     * @param onError Callback to handle exceptions that occur during execution.
     * @param inScope The CoroutineScope in which to run the function, defaults to viewModelScope.
     * @return A Job representing the coroutine.
     */
    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        inScope: CoroutineScope = viewModelScope
    ): Job {
        return runWithErrorCheck(onError, inScope) {
            val result = function()
            Log.e("Result", "tryToExecute: $result")
            onSuccess(result)
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(effect)
        }
    }

    /**
     * Executes a suspend function with error handling.
     *
     * It runs the provided suspend function within the specified CoroutineScope and dispatcher.
     * It catches exceptions that occur during the execution and handles them using the onError callback.
     * Specific exception types (IOException, IllegalArgumentException) are logged and passed to the onError callback.
     *
     * @param onError Callback to handle exceptions that occur during execution.
     * @param inScope The CoroutineScope in which to run the function, defaults to viewModelScope.
     * @param dispatcher The CoroutineDispatcher to use for the coroutine, defaults to Dispatchers.IO.
     * @param function The suspend function to execute.
     * @return A Job representing the coroutine.
     */
    private fun runWithErrorCheck(
        onError: (Exception) -> Unit,
        inScope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        function: suspend () -> Unit
    ): Job {
        return inScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> {
                        Log.d("IOException", "${exception.message}")
                        onError(exception)
                    }

                    is IllegalArgumentException -> {
                        Log.d("IllegalException", "${exception.message}")
                        onError(exception)
                    }

                    else -> {
                        Log.d("UnknownException", "${exception.message}")
                        onError(exception)
                    }
                }
            }
        }
    }

    /**
     * Collects values from a [Flow] and updates the ViewModel state accordingly.
     *
     * Collects the latest values emitted by the provided [Flow]
     * and applies given [updateState] lambda function to update the state
     * of the ViewModel. And runs in the ViewModel's scope to ensure proper
     * lifecycle management.
     *
     * @param flow The [Flow] to collect values from.
     * @param updateState A lambda function that defines how to update the state
     * with the collected value. It takes the current state and
     * the collected value as parameters and returns the new state.
     */
    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: S.(T) -> S
    ) {
        viewModelScope.launch {
            flow.collectLatest { value ->
                _state.update { it.updateState(value) }
            }
        }
    }

}