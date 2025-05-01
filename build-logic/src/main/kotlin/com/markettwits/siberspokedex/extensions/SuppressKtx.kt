package com.markettwits.siberspokedex.extensions

fun suppressExperimentalCoroutinesApi() = listOf(
    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
)