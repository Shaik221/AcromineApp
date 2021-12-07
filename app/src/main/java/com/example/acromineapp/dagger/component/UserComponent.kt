package com.example.acromineapp.dagger.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acromineapp.dagger.UserScope
import com.example.acromineapp.dagger.ViewModelKey
import com.example.acromineapp.vm.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserComponent {
    fun factory(): ViewModelProvider.Factory

    @Subcomponent.Builder
    interface Builder {
        fun build(): UserComponent
    }
}

@Module(subcomponents = [UserComponent::class])
interface UserScopeModule

@Module
interface UserModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}
