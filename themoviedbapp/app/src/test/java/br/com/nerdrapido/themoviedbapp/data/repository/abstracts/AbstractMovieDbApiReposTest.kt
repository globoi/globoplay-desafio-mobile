package br.com.nerdrapido.themoviedbapp.data.repository.abstracts

import android.app.Application
import android.content.SharedPreferences
import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
abstract class AbstractMovieDbApiReposTest : KoinTest {

    private val context = Mockito.mock(Application::class.java)

    private val sharedPrefs = Mockito.mock(SharedPreferences::class.java)

    open fun setUp() {
        val modulesCustom = mutableListOf<Module>()
        modulesCustom.addAll(KoinManager.getApplicationModules())
        modulesCustom.add(getOverrideModules())
        startKoin {
            androidContext(context)
            modules(
                modulesCustom
            )
        }
        Mockito.`when`(
            context.getSharedPreferences(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(sharedPrefs)
    }

    open fun getOverrideModules(): Module {
        return module {

        }
    }

    open fun afterEach() {
        stopKoin()
    }

    fun <T> throwWhenNotApiError(response: ResponseWrapper<T>?) {
        when (response) {
            is ResponseWrapper.NetworkError -> throw RuntimeException("NetworkError")
            is ResponseWrapper.GenericError -> return
            is ResponseWrapper.Success<T> -> throw RuntimeException("Success")
            else -> throw RuntimeException("Undefined")
        }
    }

}