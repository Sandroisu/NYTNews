package ru.slatinin.nytnews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.slatinin.nytnews.databinding.FragmentBrowserHistoryBinding

class BrowserHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBrowserHistoryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

}