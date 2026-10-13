package com.example.bitfit1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "LogDetailActivity"
//const val LOG_EXTRA = "LOG_EXTRA"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_log)
        val button = findViewById<Button>(R.id.submitButton)
        val hoursfield = findViewById<TextView>(R.id.hoursField)
        val datefield = findViewById<TextView>(R.id.dateField)
        button.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as LogApplication).db.logDao().insert(
                    LogEntity(
                        date = datefield.text.toString(),
                        hours = hoursfield.text.toString() + " hours"
                    ))
                }
            finish()
        }
    }
}
