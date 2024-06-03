package ru.otus.daggerhomework

import android.app.Application
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Named

class ViewModelReceiver @Inject constructor(
    private val state: StateFlow<Int>,
    @Named("app") private val context: Context
) {

    fun observeColors(): StateFlow<Int> {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
        return state
    }
}