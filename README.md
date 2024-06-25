# AppLink Demo

 An demo showcasing app-to-app integration for easier and more convenience to integrate with us

### Quick Pay

```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("transactionType", 1)
    putExtra("orderId", System.currentTimeMillis().toString())
    putExtra("orderTitle", "Intent Demo")
    putExtra("amount", 10)
    putExtra("printReceipt", false)
}
startActivity(i)
```

### Card Payment

```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("transactionType", 2)
    putExtra("orderId", System.currentTimeMillis().toString())
    putExtra("orderTitle", "Intent Demo")
    putExtra("amount", 100)
    putExtra("printReceipt", false)
}
startActivity(i)
```
Note: For terminal MF919 we had no control for the receipt printing.


### Void Transaction
```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("transactionType", 3)
    putExtra("transactionId", "240620034957010325054813")
    putExtra("reason", "Wrong Order")
    putExtra("email", "test@abc.my")
    putExtra("pin", "456789")
    putExtra("printReceipt", false)
}
startActivity(i)
```

### Wallet Settlement
```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("transactionType", 4)
    putExtra("print", false)
}
startActivity(i)
```

### Card Settlement
```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("transactionType", 5)
}
startActivity(i)
```

## Receive result

### [Receiver.kt](https://github.com/RevenueMonster/applink-demo/blob/master/app/src/main/java/my/revenuemonster/intentdemo/Receiver.kt)
```kotlin
class Receiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {

        try {
            val keySet: Set<String> = intent?.extras!!.keySet()
            keySet.forEach {
                Log.v("Receiver", "key = $it || value = ${intent.extras!![it]}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val result = intent?.getStringExtra("result")
        //Do your code here

    }
}
```

Note: all the result will be return as Json String

### Register receiver
```kotlin

class MainActivity : AppCompatActivity() {

    private var receiver: Receiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ...

        receiver = Receiver()
        intentFilter = IntentFilter().also {
            it.addAction("REVENUE_MONSTER_RESULT")
        }

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}


```


