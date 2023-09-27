package org.hirnyivlad.gifgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.hirnyivlad.gifgallery.R
import org.hirnyivlad.gifgallery.databinding.FragmentMainScreenBinding
import org.hirnyivlad.gifgallery.ui.adapter.GifComparator
import org.hirnyivlad.gifgallery.ui.adapter.GifAdapter

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: GifViewModel by viewModels()

    private lateinit var binding: FragmentMainScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GifAdapter(requireContext(), GifComparator) {
            val bundle = Bundle()
            bundle.putString("url", it.gifUrl)
            findNavController().navigate(R.id.gifDetailsFragment, bundle)
        }
        binding.mainRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.mainRecycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.gifPagerFlow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}