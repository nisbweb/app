package `in`.nisb.nisbapp

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging

import org.json.JSONException
import org.json.JSONObject


class DatabaseFunctions {

        private val database = "nisb"


        fun makeTables(c: Context) {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                //guest table
                db.execSQL("create table if not exists guest(Logged int)")
                //user table
                db.execSQL("create table if not exists user(email text,ieeeno int,name text,data text)")
                //notification table
                db.execSQL("create table if not exists notifs(data text)")
            } catch (e: Exception) {
            }

        }

        private val welcomeNotif: JSONObject?
            get() {
                try {
                    val jo = JSONObject()
                    jo.put("body", "Welcome to NISB App!")
                    return jo
                } catch (e: JSONException) {
                }

                return null
            }

        fun doGuestLogin(c: Context): Int {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                makeTables(c)
                db.execSQL("insert into guest values(1)")
                db.close()
                addNotification(c, welcomeNotif!!.toString())
                FirebaseMessaging.getInstance().subscribeToTopic("main")
                return 1
            } catch (e: Exception) {
                return 0
            }

        }

        fun doUserLogin(c: Context, email: String, ieeeno: String, wname: String, data: String): Int {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                makeTables(c)
                db.execSQL("insert into user values('$email',$ieeeno,'$wname','$data')")
                db.close()
                addNotification(c, welcomeNotif!!.toString())
                FirebaseMessaging.getInstance().subscribeToTopic("main")
                return 1
            } catch (e: Exception) {
                return 0
            }

        }

        fun isGuestLogged(c: Context): Boolean {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from guest", null)
                val num_rows = rs.count
                db.close()
                if (num_rows > 0)
                    return true
            } catch (e: Exception) {
                return false
            }

            return false
        }

        fun isUserLogged(c: Context): Boolean {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from user", null)
                val num_rows = rs.count
                db.close()
                if (num_rows > 0)
                    return true
            } catch (e: Exception) {
                return false
            }

            return false
        }

        fun getUserEmail(c: Context): String {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from user", null)
                val num_rows = rs.count
                db.close()
                if (num_rows > 0) {
                    rs.moveToFirst()
                    val e = rs.getString(0)
                    return e
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "Error 2"
        }

        fun getUserName(c: Context): String {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from user", null)
                val num_rows = rs.count
                db.close()
                if (num_rows > 0) {
                    rs.moveToFirst()
                    val e = rs.getString(2)
                    rs.close()
                    return e
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "Error 2"
        }

        fun getUserData(c: Context): JSONObject? {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from user", null)
                val num_rows = rs.count
                db.close()
                if (num_rows > 0) {
                    rs.moveToFirst()
                    val e = rs.getString(3)
                    return JSONObject(e)
                }
            } catch (e: Exception) {
                return null
            }

            return null
        }

        fun getUserIEEEno(c: Context): Int {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                makeTables(c)
                val rs = db.rawQuery("select * from user", null)
                val num_rows = rs.count
                db.close()

                if (num_rows > 0) {
                    rs.moveToFirst()
                    val e = rs.getInt(1)
                    return e
                }
            } catch (e: Exception) {
                //return e.getMessage();
                return 0
            }

            return 0
        }

        fun doGuestLogout(c: Context): Int {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                db.execSQL("Delete from guest")
                db.close()
                clearNotifications(c)
                return 1
            } catch (e: Exception) {
                return 0
            }

        }

        fun doUserLogout(c: Context): Int {
            try {
                val db = c.openOrCreateDatabase("nisb", Context.MODE_PRIVATE, null)
                db.execSQL("Delete from user")
                db.close()
                clearNotifications(c)
                return 1
            } catch (e: Exception) {
                return 0
            }

        }

        fun addNotification(c: Context, data: String) {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                db.execSQL("insert into notifs values('$data')")
            } catch (e: Exception) {
            }

        }

        fun getNotifications(c: Context): Array<JSONObject?>? {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                val rs = db.rawQuery("select * from notifs", null)
                val num_rows = rs.count
                db.close()

                if (num_rows > 0) {
                    val vals = arrayOfNulls<JSONObject>(num_rows)
                    rs.moveToFirst()
                    for (i in 0..rs.count - 1) {
                        rs.moveToPosition(i)
                        val jobj1 = JSONObject(rs.getString(0))
                        vals[i] = jobj1
                    }
                    return vals
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun clearNotifications(c: Context) {
            try {
                val db = c.openOrCreateDatabase(database, Context.MODE_PRIVATE, null)
                db.execSQL("delete from notifs")
            } catch (e: Exception) {
            }

        }

}//constructor
