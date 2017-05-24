package `in`.nisb.nisbapp

import android.util.Log

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by mridul on 21/3/17.
 */

class TokenService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val fcmToken = FirebaseInstanceId.getInstance().token

        Log.d("", "FCM Device Token:" + fcmToken!!)
        //Save or send FCM registration token

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("guest_tokens")
        ref.push().setValue(fcmToken)

    }


}
