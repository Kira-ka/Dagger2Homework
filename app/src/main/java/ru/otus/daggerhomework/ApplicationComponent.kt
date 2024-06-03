package ru.otus.daggerhomework

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Named

@Component
interface ApplicationComponent {

    companion object {
        fun init(context: Context): ApplicationComponent {
            return DaggerApplicationComponent.factory().create(context)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance @Named("app") context: Context): ApplicationComponent
    }

    @Named("app")
    fun provideApp(): Context
}