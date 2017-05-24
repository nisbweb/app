package `in`.nisb.nisbapp.volley

import `in`.nisb.nisbapp.BackendVolley
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

/**
 * Created by mridul on 5/22/17.
 */
class VolleyService : VolleyServiceInterface {
    val TAG = VolleyService::class.java.simpleName
    val basePath = "" //    http://nisb.in


//    Use this function to make post requests with JSON info
//    Usage :
//    val params = JSONObject("{jsonkey:jsonval}")
//
//    APIController(VolleyService()).post_json("http://url.com",params,{
//        response ->
//        //when you get the response
//    }
    override fun post_json(path: String, params: JSONObject?, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, basePath + path, params,
                Response.Listener<JSONObject> { response ->  completionHandler(response) },
                Response.ErrorListener { error -> completionHandler(null) })
                {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers.put("Content-Type", "application/json")
                        return headers
                    }
                }
        jsonObjReq.setShouldCache(false)
        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }



//    Use this function to make post requests to url with params having the data
//    Usage :
//    val params = HashMap<String, String>()
//    params.put("email", email)
//
//    APIController(VolleyService()).post_string("http://url.com",params,{
//        response ->
//        //when you get the response
//    }
    override fun post_string(path: String, params: HashMap<String, String>?, completionHandler: (response: String?) -> Unit) {
        val stringObjReq = object : StringRequest(Method.POST,basePath + path,
                Response.Listener<String> { response -> completionHandler(response) },
                Response.ErrorListener {  error -> completionHandler(null) })
                {
                    @Override override fun getParams(): MutableMap<String, String> {
                        return if (params!=null) params else super.getParams()
                    }
                }
        stringObjReq.setShouldCache(false)
        BackendVolley.instance?.addToRequestQueue(stringObjReq, TAG)
    }




//    Use this function to make get requests to any urls
//    Usage :
//    APIController(VolleyService()).get_string("http://url.com",{
//        response ->
//        //when you get the response
//    })
    override fun get_string(path: String, completionHandler: (response: String?) -> Unit) {
        val stringObjReq = object : StringRequest(Method.GET,basePath + path,
                Response.Listener<String> { response -> completionHandler(response) },
                Response.ErrorListener {  error -> Log.d("Volley ERROR", error.toString());
//                    completionHandler(null)
                }) { }
        stringObjReq.setShouldCache(false)
        BackendVolley.instance?.addToRequestQueue(stringObjReq, TAG)
    }


}