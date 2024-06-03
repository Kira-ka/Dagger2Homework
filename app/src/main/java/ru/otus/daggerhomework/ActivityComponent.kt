package ru.otus.daggerhomework

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Named
import javax.inject.Singleton

@ActivityScope
@Component(
    modules = [Observer::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    companion object {
        fun init(appComponent: ApplicationComponent, context: Context): ActivityComponent {
            return DaggerActivityComponent.factory().create(appComponent, context)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            appComponent: ApplicationComponent,
            @BindsInstance @Named("activity") context: Context
        ): ActivityComponent
    }

    @Named("app")
    fun provideApp(): Context

    @Named("activity")
    fun provideActivity(): Context

    fun provideMutableObserver(): MutableStateFlow<Int>

    fun provideObserver(): StateFlow<Int>

    fun inject(activity: MainActivity)
}

@Module
object Observer {

    @ActivityScope
    @Provides
    fun provideMutableStateFlow(): MutableStateFlow<Int> {
        return MutableStateFlow(0)
    }

    @ActivityScope
    @Provides
    fun provideStateFlow(mutableStateFlow: MutableStateFlow<Int>): StateFlow<Int> {
        return mutableStateFlow.asStateFlow()
    }
}
