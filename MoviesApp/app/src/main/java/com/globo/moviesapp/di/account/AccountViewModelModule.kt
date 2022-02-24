package com.globo.moviesapp.di.account

import androidx.lifecycle.ViewModel
import com.globo.moviesapp.di.ViewModelKey
import com.globo.moviesapp.ui.account.viewmodel.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AccountViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindMovieViewModel(viewModel: AccountViewModel): ViewModel
}