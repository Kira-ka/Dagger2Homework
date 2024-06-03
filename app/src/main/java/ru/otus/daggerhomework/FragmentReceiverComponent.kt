package ru.otus.daggerhomework

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@ProducerScope
@Component(
    dependencies = [ActivityComponent::class]
)
interface FragmentReceiverComponent {

    companion object {

        fun init(activityComponent: ActivityComponent): FragmentReceiverComponent {
            return DaggerFragmentReceiverComponent.factory().create(activityComponent)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(activityComponent: ActivityComponent): FragmentReceiverComponent
    }

    fun inject(fragmentReceiver: FragmentReceiver)
}