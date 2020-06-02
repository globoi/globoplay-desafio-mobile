package me.davidpcosta.tmdb.ui.main.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.model.AccountDetails
import me.davidpcosta.tmdb.data.repository.AccountRepository

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    val accountDetails: LiveData<AccountDetails> = MutableLiveData()

    fun fetchAccountDetails(sessionId: String) {
        accountRepository.accountDetails(sessionId).subscribe({
            accountDetails as MutableLiveData
            accountDetails.value = it
        },
            { e ->
                e.printStackTrace()
            })
    }

}