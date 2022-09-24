package com.melvin.ongandroid.view.principal.nosotros

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.MiembroFragmentBinding


class MiembroDialogo : DialogFragment() {

   lateinit var binding : MiembroFragmentBinding

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View {

       binding = MiembroFragmentBinding.inflate(inflater, container, false)

       /** se reciben los datos de nosotros fragment y se muestran en pantalla*/

       setFragmentResultListener("requestKey1",) { key, bundle ->
           val resultImagen = bundle.getString("bundleKey1")
           context?.let { Glide.with(it).load(resultImagen).into(binding.ivFotoStaff) }
       }
       setFragmentResultListener("requestKey2",) { key, bundle ->
           val resultNombre = bundle.getString("bundleKey2")
           binding.tvNombre.text = resultNombre }

       setFragmentResultListener("requestKey3",) { key, bundle ->
           val resultDescripcion = bundle.getString("bundleKey3")
           binding.tvDescripcionStaff.text = resultDescripcion }

       setFragmentResultListener("requestKey4",) { key, bundle ->
           val resultUrlFace = bundle.getString("bundleKey4")
           binding.tvFacebook.text = resultUrlFace
       }
       setFragmentResultListener("requestKey5",) { key, bundle ->
           val resultUrlLi = bundle.getString("bundleKey5")
           binding.tvLinkedin.text = resultUrlLi
       }
       return binding.root
   }

}






