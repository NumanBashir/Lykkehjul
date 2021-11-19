package com.example.lykkehjul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class VundetFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_vundet,container,false)

        val GåTilbageKnap: Button = rootView.findViewById(R.id.GåTilbageKnap)
        GåTilbageKnap.setOnClickListener {
            dismiss()
        }

        return rootView
    }

}