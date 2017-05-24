package `in`.nisb.nisbapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.messaging.RemoteMessage
import com.squareup.picasso.Picasso
import java.util.UUID

import android.net.ConnectivityManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import java.math.BigInteger
import java.security.MessageDigest


/**
 * Created by mridul on 23/3/17.
 */

object ExtraFunctions {

    fun setSBColor(w: Window, c: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            w.statusBarColor = c
        }
    }

    fun fbUrlGenerator(path: String, fields: Array<String>): String {
        return "https://graph.facebook.com/v2.8/$path?fields=" + strJoin("%2C", fields) +
                "&access_token=1327383467301154%7CYDfQ94wTelbffydG5XrnanHnqu0"
    }

    fun strJoin(s: String, fields: Array<String>): String {
        var c = ""
        for (a in fields) {
            c += a + s
        }
        return c.substring(0, c.length - s.length)
    }

    fun sendNotification(mcontext: Context, title: String?, message: String?, intent: Intent) {
        val color = Color.RED
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val requestID = System.currentTimeMillis().toInt()
        val pendingIntent = PendingIntent.getActivity(mcontext, requestID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(mcontext)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.nisblogo)
                .setColor(color)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = mcontext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }

    fun isConnected(c: Context): Boolean {
        // get Connectivity Manager object to check connection
        val connec = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connec.getNetworkInfo(0).state == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).state == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).state == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).state == android.net.NetworkInfo.State.CONNECTED) {

            return true

        } else if (connec.getNetworkInfo(0).state == android.net.NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).state == android.net.NetworkInfo.State.DISCONNECTED) {

            Toast.makeText(c, "No Internet.", Toast.LENGTH_LONG).show()
            return false
        }
        return false
    }



    fun getMD5(plaintext: String) : String{
//        val plaintext = "your text here"
        val m: MessageDigest = MessageDigest.getInstance("MD5")
        m.reset()
        m.update(plaintext.toByteArray())
        val digest = m.digest()
        val bigInt = BigInteger(1, digest)
        var hashtext = bigInt.toString(16)
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length < 32) {
            hashtext = "0" + hashtext
        }
        return hashtext
    }

    fun userTokenUpdate(c: Context){
        if (DatabaseFunctions().isUserLogged(c)){
            val fcmToken = FirebaseInstanceId.getInstance().token
            val database = FirebaseDatabase.getInstance()
            val ref = database.getReference("user_tokens/${getMD5(DatabaseFunctions().getUserEmail(c))}/")
            ref.setValue(fcmToken)
        }
    }

}
