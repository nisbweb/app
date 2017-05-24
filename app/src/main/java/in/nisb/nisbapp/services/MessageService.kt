package `in`.nisb.nisbapp

import android.content.Intent

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import org.json.JSONException
import org.json.JSONObject


class MessageService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        var intent = Intent(applicationContext, MainActivity::class.java)
        try {
            val jo = JSONObject()
            jo.put("context", remoteMessage!!.data["context"])
            jo.put("body", remoteMessage.data["body"])

            if (remoteMessage.data["context"] == "event-single") {
                intent = Intent(applicationContext, EventSingleActivity::class.java)
                intent.putExtra("id", remoteMessage.data["id"])
                jo.put("id", remoteMessage.data["id"])
            }


            if (remoteMessage.data["context"] == "blog-single") {
                intent = Intent(applicationContext, BlogSingleActivity::class.java)
                intent.putExtra("url", remoteMessage.data["blogurl"])
                jo.put("url", remoteMessage.data["blogurl"])
            }

            //Save the info to the DB
            DatabaseFunctions().addNotification(applicationContext, jo.toString())
        } catch (j: JSONException) {

        }

        //Show the Notification
        ExtraFunctions.sendNotification( applicationContext,
                "NISB", remoteMessage!!.data["body"], intent)
    }

}
