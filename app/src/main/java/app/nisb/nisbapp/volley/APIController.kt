package app.nisb.nisbapp.volley

import org.json.JSONObject

/**
 * Created by mridul on 5/22/17.
 */
class APIController constructor(volleyServiceInjection: VolleyServiceInterface): VolleyServiceInterface {
    private val volleyService: VolleyServiceInterface = volleyServiceInjection

    override fun post_json(path: String, params: JSONObject?, completionHandler: (response: JSONObject?) -> Unit) {
        volleyService.post_json(path, params, completionHandler)
    }

    override fun post_string(path: String, params: HashMap<String,String>?, completionHandler: (response: String?) -> Unit){
        volleyService.post_string(path, params, completionHandler)
    }

    override fun get_string(path: String, completionHandler: (response: String?) -> Unit) {
        volleyService.get_string(path,completionHandler)
    }
}