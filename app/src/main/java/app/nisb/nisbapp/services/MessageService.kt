package app.nisb.nisbapp

import android.content.Intent
import android.util.Log
import android.widget.Toast

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import org.json.JSONException
import org.json.JSONObject


class MessageService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

      Log.d("GOT A MESSAGE","");
     // Toast.makeText(applicationContext,"GOT MSG",Toast.LENGTH_SHORT).show()
        val jo = JSONObject()
        var intent = Intent(applicationContext, MainActivity::class.java)
        try {

            val context :String? = remoteMessage.data["context"]
            val ids : String? = remoteMessage.data["id"]
            jo.put("context", context)
            jo.put("body", remoteMessage.data["body"])
            jo.put("id", ids)

            if (context.equals("event")){
                val intent = Intent(applicationContext, EventSingle::class.java)
                intent.putExtra("id", ids)
            }
            else if (context.equals("blog")){
                val intent = Intent(applicationContext, BlogSingle::class.java)
                intent.putExtra("id", ids)
            }
            DBFunc().addNotification(applicationContext, jo.toString())

        }
        catch (j: JSONException) {
            System.out.println(j.toString())
        }




        //Show the Notification
        ExtraFunctions.sendNotification( applicationContext,
                "NISB", remoteMessage!!.data["body"], intent)
    }

}
