package com.ftoniolo.testing

import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
@Suppress("MatchingDeclarationName")
class MainCoroutineRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
) : TestWatcher() {

    val testDispatcherProvider = object : CoroutinesDispatchers {
        override fun default(): CoroutineDispatcher = testDispatcher
        override fun io(): CoroutineDispatcher = testDispatcher
        override fun main(): CoroutineDispatcher = testDispatcher
        override fun unconfined(): CoroutineDispatcher = testDispatcher
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}