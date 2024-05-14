package ru.otus.daggerhomework

import android.app.Application

class App : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = ApplicationComponent.init(this)
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }
}