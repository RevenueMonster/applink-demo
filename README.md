# AppLink Demo

An demo showcasing app-to-app integration for easier and more convenience to integrate with us

### Quick Pay

```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("packageName", packageName)
    putExtra("receiverName", ReceiverActivity::class.java.name)
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
    putExtra("packageName", packageName)
    putExtra("receiverName", ReceiverActivity::class.java.name)
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
    putExtra("packageName", packageName)
    putExtra("receiverName", ReceiverActivity::class.java.name)
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
    putExtra("packageName", packageName)
    putExtra("receiverName", ReceiverActivity::class.java.name)
    putExtra("transactionType", 4)
    putExtra("print", false)
}
startActivity(i)
```

### Card Settlement

```kotlin
val i = Intent("REVENUE_MONSTER_PAYMENT").apply {
    putExtra("packageName", packageName)
    putExtra("receiverName", ReceiverActivity::class.java.name)
    putExtra("transactionType", 5)
}
startActivity(i)
```

## Receive result

### AndroidManifest

```xml
<activity android:name=".ReceiverActivity" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.SEND" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:mimeType="text/plain" />
    </intent-filter>
</activity>
```

### ReceiverActivity

```kotlin
class ReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //....

        try {
            val keySet: Set<String> = intent?.extras!!.keySet()
            keySet.forEach {
                Log.v("Receiver", "key = $it || value = ${intent.extras!![it]}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val result = intent?.getStringExtra("result")
        val transactionType = intent?.getIntExtra("transactionType")
        //Do your code here

    }
}
```

Note: all the result will be return as Json String


