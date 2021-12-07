package com.example.acromineapp

import android.app.Application
import com.example.acromineapp.dagger.component.AppComponent
import com.example.acromineapp.dagger.component.DaggerAppComponent
import com.example.acromineapp.dagger.module.AppModule
import com.example.acromineapp.dagger.module.NetworkModule

class BaseApplication : Application() {
    private lateinit var mApiComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mApiComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule("http://www.nactem.ac.uk/software/acromine/"))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return mApiComponent
    }
}