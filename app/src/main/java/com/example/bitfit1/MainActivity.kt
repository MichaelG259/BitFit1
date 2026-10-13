package com.example.bitfit1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val logs = mutableListOf<LogItem>()
    private lateinit var logsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        logsRecyclerView = findViewById(R.id.logs)

        val logAdapter = LogAdapter(logs)
        logsRecyclerView.adapter = logAdapter

        logsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            logsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            this.startActivity(intent)
        }

        lifecycleScope.launch {
            (application as LogApplication).db.logDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    LogItem(
                        entity.date,
                        entity.hours,
                    )
                }.also { mappedList ->
                    logs.clear()
                    logs.addAll(mappedList)
                    logAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}