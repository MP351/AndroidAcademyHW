package com.example.androidacademyhw

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

val KEY_MESSAGE = "MSG_KEY"

class AboutActivity : AppCompatActivity() {
    private lateinit var btnSend: Button
    private lateinit var etMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        etMessage = findViewById(R.id.et_message)
        val message = etMessage.text.toString()
        btnSend = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(KEY_MESSAGE, message)
                type = "text/plain"
            }

            if (intent.resolveActivity(packageManager) != null)
                startActivity(intent)
            else
                Toast.makeText(this, getString(R.string.no_app_found), Toast.LENGTH_LONG).show()
        }

        supportActionBar?.title = getString(R.string.title)
    }

    companion object {
        fun openActivity(activity: Activity) {
            val intent = Intent(activity, AboutActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
