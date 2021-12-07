package com.example.acromineapp.dagger.component

import com.example.acromineapp.MainActivity
import com.example.acromineapp.dagger.module.NetworkModule
import com.example.acromineapp.dagger.module.AppModule
import com.example.acromineapp.vm.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}