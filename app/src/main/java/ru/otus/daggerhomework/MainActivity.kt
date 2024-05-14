package ru.otus.daggerhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import java.lang.Exception

class MainActivity : AppCompatActivity(), FragmentProducerNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appProvider = (application as? App)?.getAppComponent() ?: throw Exception(
            WRONG_CAST_EXCEPTION
        )

        activityComponent = ActivityComponent.init(appProvider, this).also { it.inject(this) }

        if (supportFragmentManager.findFragmentByTag(FragmentProducer.TAG) == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.root_container, FragmentProducer.newInstance(), FragmentProducer.TAG)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityComponent = null
    }

    companion object {
        private const val WRONG_CAST_EXCEPTION = "WRONG_CAST_EXCEPTION"

        private var activityComponent: ActivityComponent? = null

        val component
            get() = activityComponent
                ?: throw IllegalArgumentException("Component must not be null")
    }

    override fun goToFragmentReceiver() {
        supportFragmentManager.commit {
            replace(R.id.root_container, FragmentReceiver.newInstance(), FragmentReceiver.TAG)
            addToBackStack(FragmentReceiver.TAG)
        }
    }
}