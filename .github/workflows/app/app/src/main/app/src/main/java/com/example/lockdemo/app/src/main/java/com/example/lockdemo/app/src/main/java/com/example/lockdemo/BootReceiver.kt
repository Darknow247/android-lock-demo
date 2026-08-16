package com.example.lockdemo

import android.app.admin.DevicePolicyManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val admin = ComponentName(context, AdminReceiver::class.java)

        if (!dpm.isAdminActive(admin)) return

        if (action == Intent.ACTION_BOOT_COMPLETED || action == "android.intent.action.QUICKBOOT_POWERON") {
            dpm.lockNow()
        }

        if (action == Intent.ACTION_BOOT_COMPLETED || action == Intent.ACTION_USER_PRESENT) {
            val i = Intent(context, UnlockActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(i)
        }
    }
}
