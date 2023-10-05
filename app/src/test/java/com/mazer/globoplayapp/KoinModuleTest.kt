package com.mazer.globoplayapp

import com.mazer.globoplayapp.presentation.di.appModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KoinModuleTest : KoinTest {

    @Test
    fun checkAllModules() {
        appModule.verify()
    }
}