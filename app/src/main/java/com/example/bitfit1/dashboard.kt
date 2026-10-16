package com.example.bitfit1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private val logs = mutableListOf<LogItem>()
    private lateinit var logsRecyclerView: RecyclerView
    private lateinit var logsAdapter: LogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = requireActivity().application as LogApplication
        lifecycleScope.launch {
            app.db.logDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    LogItem(
                        entity.date,
                        entity.hours,
                    )
                }.also { mappedList ->
                    logs.clear()
                    logs.addAll(mappedList)
                    logsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val layoutManager = LinearLayoutManager(context)
        logsRecyclerView = view.findViewById(R.id.parks)
        logsRecyclerView.layoutManager = layoutManager
        logsRecyclerView.setHasFixedSize(true)
        logsAdapter = LogAdapter(logs)
        logsRecyclerView.adapter = logsAdapter

        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            this.startActivity(intent)
        }

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}