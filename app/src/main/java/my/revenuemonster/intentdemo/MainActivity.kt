package my.revenuemonster.intentdemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import my.revenuemonster.intentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var receiver: Receiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        receiver = Receiver()
        intentFilter = IntentFilter().also {
            it.addAction("REVENUE_MONSTER_RESULT")
        }

        binding.btnQuickPay.setOnClickListener {
            val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
                putExtra("transactionType", 1)
                putExtra("orderId", System.currentTimeMillis().toString())
                putExtra("orderTitle", "Intent Demo")
                putExtra("amount", 10)
                putExtra("printReceipt", false)
            }
            startActivity(i)
        }

        binding.btnCard.setOnClickListener {
            val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
                putExtra("transactionType", 2)
                putExtra("orderId", System.currentTimeMillis().toString())
                putExtra("orderTitle", "Intent Demo")
                putExtra("amount", 100)
                putExtra("printReceipt", false)
            }
            startActivity(i)
        }

        binding.btnVoid.setOnClickListener {
            val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
                putExtra("transactionType", 3)
                putExtra("transactionId", "240620034957010325054813")
                putExtra("reason", "Wrong Order")
                putExtra("email", "test@abc.my")
                putExtra("pin", "456789")
                putExtra("printReceipt", false)
            }
            startActivity(i)
        }

        binding.btnWalletSettlement.setOnClickListener {
            val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
                putExtra("transactionType", 4)
                putExtra("print", false)
            }
            startActivity(i)
        }

        binding.btnCardSettlement.setOnClickListener {
            val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
                putExtra("transactionType", 5)
            }
            startActivity(i)
        }

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}