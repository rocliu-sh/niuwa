package com.niuwa

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Toast
import com.niuwa.appreciation.AppreciationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)

        // Enables Always-on
        setAmbientEnabled()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.button1 -> {
                    startActivity(Intent(this@MainActivity, AppreciationActivity::class.java))
                }
                R.id.button2 -> {

                }
                R.id.button3 -> {

                }
            }
        }
    }
}