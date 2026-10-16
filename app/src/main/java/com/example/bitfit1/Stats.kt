package com.example.bitfit1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        val app = requireActivity().application as LogApplication

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            this.startActivity(intent)
        }

        val clearbutton = view.findViewById<Button>(R.id.clearbutton)
        clearbutton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    app.db.logDao().deleteAll()
                }
            }
        }

        val avgLabel = view.findViewById<TextView>(R.id.avgHours)
        val minLabel = view.findViewById<TextView>(R.id.minHours)
        val maxLabel = view.findViewById<TextView>(R.id.maxHours)
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                avgLabel.text = app.db.logDao().getAvg().toString()
                minLabel.text = app.db.logDao().getMin().toString()
                maxLabel.text = app.db.logDao().getMax().toString()
            }
        }

        return view
    }

    companion object {
        fun newInstance(): StatsFragment {
            return StatsFragment()
        }
    }
}