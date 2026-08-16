package com.example.lockdemo

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var dpm: DevicePolicyManager
    private lateinit var admin: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dpm = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        admin = ComponentName(this, AdminReceiver::class.java)

        findViewById<Button>(R.id.btnActivate).setOnClickListener {
            val i = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin)
                putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "Demo educativa: control de bloqueo de pantalla")
            }
            startActivity(i)
        }

        findViewById<Button>(R.id.btnLock).setOnClickListener {
            if (dpm.isAdminActive(admin)) {
                dpm.lockNow()
                startActivity(Intent(this, UnlockActivity::class.java))
            } else {
                Toast.makeText(this, "Primero activa el administrador", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
