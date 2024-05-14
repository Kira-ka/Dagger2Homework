package ru.otus.daggerhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentReceiver : Fragment(R.layout.fragment_b) {

    @Inject
    lateinit var viewModel: ViewModelReceiver
    private lateinit var frame: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.component.let { FragmentReceiverComponent.init(it).inject(this) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frame = view.findViewById(R.id.frame)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeColors().collect { color ->
                    populateColor(color)
                }
            }
        }
    }

    fun populateColor(@ColorInt color: Int) {
        frame.setBackgroundColor(color)
    }

    companion object {

        const val TAG = "FragmentReceiver"

        @JvmStatic
        fun newInstance() = FragmentReceiver()
    }
}