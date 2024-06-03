package ru.otus.daggerhomework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import javax.inject.Inject

class FragmentProducer : Fragment(R.layout.fragment_a) {

    @Inject
    lateinit var viewModel: ViewModelProducer


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is FragmentProducerNavigation) {
            throw RuntimeException(FragmentProducer.ERROR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.component.let { FragmentProducerComponent.init(it).inject(this) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigateButton = view.findViewById<Button>(R.id.navigation_button)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.generateColor()
            navigateButton.isEnabled = true
        }

        navigateButton.setOnClickListener {
            (requireContext() as FragmentProducerNavigation).goToFragmentReceiver()
            it.isEnabled = false
        }

    }

    companion object {

        const val TAG = "FragmentProducer"
        private const val ERROR = "ERROR FragmentProducer"

        @JvmStatic
        fun newInstance() = FragmentProducer()
    }
}