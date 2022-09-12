package com.melvin.ongandroid.view.principal.nosotros

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.MiembroFragmentBinding
import com.melvin.ongandroid.model.Miembros

class MiembroFragment : Fragment() {

    lateinit var binding: MiembroFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MiembroFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            Glide.with(requireContext())
                .load(requireArguments().getString("picture"))
                .into(ivFotoStaff)
            tvNombre.text = requireArguments().getString("name", "*Nombre*")
            tvDescripcionStaff.text = requireArguments().getString("descripcion", "*No role*")
            tvFacebook.text = requireArguments().getString("facebookLink", "")
            tvLinkedin.text = requireArguments().getString("linkedinLink", "")
        }
        val facebookUrl = requireArguments().getString("facebookLink")
        val linkedinUrl = requireArguments().getString("linkedinLink")
        facebookUrl?.let { url ->
            binding.tvFacebook.setOnClickListener(View.OnClickListener {
                launchBrowser(url)
            })
        }
        linkedinUrl?.let { url ->
            binding.tvLinkedin.setOnClickListener(View.OnClickListener {
                launchBrowser(url)
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun launchBrowser(url: String) {
        var finalURL = url
        val browserIntent = Intent(Intent.ACTION_VIEW)
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            finalURL = "http://$url";
        browserIntent.data = Uri.parse(finalURL)
        startActivity(browserIntent)
    }
}





    