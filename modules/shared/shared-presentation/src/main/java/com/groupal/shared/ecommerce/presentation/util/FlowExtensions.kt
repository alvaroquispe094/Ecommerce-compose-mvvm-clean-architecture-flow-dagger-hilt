package com.groupal.shared.ecommerce.presentation.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * [rememberFlow] is used to encapsulate the [remember] function regarding a flow and the scope for
 * it, when the flow will start to be collected and when not.
 *
 * @param flow - Basically it is a [Flow]
 *
 * @return [Flow] - Necessary to collect
 */
@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Flow<T> {
    return remember(key1 = flow, key2 = lifecycleOwner) {
        flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
}

/**
 * [Flow.collectAsStateLifecycleAware] is an extension function that helps us with the boilerplate
 * by writing a [remember] and [collectAsState] when we use a [Flow].
 *
 * @param initial - It's the same value as the [Flow] in the [ViewModel]
 * @param context - [CoroutineContext] to define the scope where the [Flow.collect] will be executed
 *
 * @return [State] - Necessary to know the screen state in the [Composable] function
 */
@Composable
fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
): State<R> {
    val lifecycleAwareFlow = rememberFlow(flow = this)
    return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
}

/**
 * [StateFlow.collectAsStateLifecycleAware] is an extension function that encapsulates
 * [Flow.collectAsStateLifecycleAware].
 *
 * @param context - [CoroutineContext] to define the scope where the [Flow.collect] will be executed
 *
 * @return [State] - Necessary to know the screen state in the [Composable] function
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext = EmptyCoroutineContext
): State<T> = collectAsStateLifecycleAware(initial = value, context)