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

            jo.put("context", remoteMessage.data["context"])
            jo.put("body", remoteMessage.data["body"])
            jo.getString("context")
//
//            if (jo.getString("context").equals("blog")) {
//                intent = Intent(applicationContext, BlogSingle::class.java)
//                intent.putExtra("id", remoteMessage.data["blogid"])
////                jo.put("id", remoteMessage.data["blogid"])
//            }
//            if (jo.getString("context").equals("event")) {
//                intent = Intent(applicationContext, EventSingle::class.java)
//                intent.putExtra("id", remoteMessage.data["id"])
////                jo.put("id", remoteMessage.data["id"])
//            }



            //Save the info to the DB

        }
        catch (j: JSONException) {
            System.out.println(j.toString())
        }


        DBFunc().addNotification(applicationContext, jo.toString())

        //Show the Notification
        ExtraFunctions.sendNotification( applicationContext,
                "NISB", remoteMessage!!.data["body"], intent)
    }

}
