package my.revenuemonster.intentdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Receiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {

        Log.v("Receiver", "==========================================")
        try {
            val keySet: Set<String> = intent?.extras!!.keySet()
            keySet.forEach {
                Log.v("Receiver", "key = $it || value = ${intent.extras!![it]}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.v("Receiver", "==========================================")

    }
}