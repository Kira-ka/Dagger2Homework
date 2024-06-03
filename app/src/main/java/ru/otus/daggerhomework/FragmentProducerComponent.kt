package ru.otus.daggerhomework

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@ProducerScope
@Component(
    modules = [ColorGeneratorModule::class],
    dependencies = [ActivityComponent::class]
)
interface FragmentProducerComponent {

    companion object {

        fun init(activityComponent: ActivityComponent): FragmentProducerComponent {
            return DaggerFragmentProducerComponent.factory().create(activityComponent)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(activityComponent: ActivityComponent): FragmentProducerComponent
    }

    fun inject(fragmentProducer: FragmentProducer)
}

@Module
interface ColorGeneratorModule {

    @Binds
    fun provideColorGenerator(colorGenerator: ColorGeneratorImpl): ColorGenerator
}