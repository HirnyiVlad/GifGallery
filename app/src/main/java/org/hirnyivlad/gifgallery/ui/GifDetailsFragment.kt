package org.hirnyivlad.gifgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import org.hirnyivlad.gifgallery.databinding.FragmentGifDetailsBinding


class GifDetailsFragment : Fragment() {
    private lateinit var binding: FragmentGifDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGifDetailsBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receivedData = arguments?.getString("url")
        if (receivedData != null) {
            Glide.with(this).asGif().load(receivedData).into(binding.fullscreenGif)
        } else {
            Toast.makeText(
                requireContext(),
                "Something went wrong, try again", Toast.LENGTH_LONG
            ).show()
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}