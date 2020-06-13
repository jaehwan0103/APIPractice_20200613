package kr.ptus.apipractice_20200613.util

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil  {


    companion object{
         val BASE_URL = "http://15.165.177.142"
        fun postRequestLogin(context: Context , id : String , pw : String , handler : JsonResponseHandler?){
            val client = OkHttpClient()
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

            val request = Request.Builder().url(urlString).post(formData)/*.header()*/.build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {



                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val josn = JSONObject(bodyString)

                    handler?.onResponse(josn)

                }

            })

        }
    }

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }


}