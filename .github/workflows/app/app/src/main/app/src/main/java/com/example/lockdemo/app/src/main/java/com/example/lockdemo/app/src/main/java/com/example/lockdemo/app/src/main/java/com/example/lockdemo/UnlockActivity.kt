package com.example.lockdemo

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UnlockActivity : Activity() {

    private val PIN = "66330"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)

        setContentView(R.layout.activity_unlock)

        val etPin = findViewById<EditText>(R.id.etPin)
        val btnUnlock = findViewById<Button>(R.id.btnUnlock)

        btnUnlock.setOnClickListener {
            if (etPin.text.toString() == PIN) {
                val dpm = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
                dpm.removeActiveAdmin(ComponentName(this, AdminReceiver::class.java))
                Toast.makeText(this, "Desbloqueado. Ya puedes desinstalar la demo.", Toast.LENGTH_LONG).show()
                finishAffinity()
            } else {
                Toast.makeText(this, "PIN incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() { }
}
