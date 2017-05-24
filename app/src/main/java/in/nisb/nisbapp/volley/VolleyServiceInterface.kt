package `in`.nisb.nisbapp.volley

import org.json.JSONObject

/**
 * Created by mridul on 5/22/17.
 */
interface VolleyServiceInterface {
    fun post_json(path: String, params: JSONObject?, completionHandler: (response: JSONObject?) -> Unit)
    fun post_string(path: String, params: HashMap<String,String>?, completionHandler: (response: String?) -> Unit)
    fun get_string(path: String, completionHandler: (response: String?) -> Unit)
}