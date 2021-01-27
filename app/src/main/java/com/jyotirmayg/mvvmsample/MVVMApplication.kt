package com.jyotirmayg.mvvmsample

import android.app.Application
import com.jyotirmayg.mvvmsample.data.db.AppDatabase
import com.jyotirmayg.mvvmsample.data.network.MyAPICalls
import com.jyotirmayg.mvvmsample.data.network.NetworkConnectionInterceptor
import com.jyotirmayg.mvvmsample.data.preferences.PreferenceProvider
import com.jyotirmayg.mvvmsample.data.repositories.QuoteRepository
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository
import com.jyotirmayg.mvvmsample.ui.auth.AuthViewModelFactor
import com.jyotirmayg.mvvmsample.ui.main.profile.ProfileViewModelFactor
import com.jyotirmayg.mvvmsample.ui.main.quotes.QuotesViewModelFactor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyAPICalls(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }

        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance(), instance()) }

        bind() from provider { AuthViewModelFactor(instance()) }
        bind() from provider { ProfileViewModelFactor(instance()) }
        bind() from provider { QuotesViewModelFactor(instance()) }
    }

}