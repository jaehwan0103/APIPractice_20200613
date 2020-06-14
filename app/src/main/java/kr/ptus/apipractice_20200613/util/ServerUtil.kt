package kr.ptus.apipractice_20200613.util

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {


    companion object {
        val BASE_URL = "http://15.165.177.142"

        fun getRequestDuplicatedCheck(
            context: Context, checkType: String, inputVal: String, handler: JsonResponseHandler?
        ) {

            val client = OkHttpClient()

//            GET방식은 어디로 갈지 주소 + 어떤 데이터를 보낼지 같이 표시됨. 주소를 만들때 데이터 첨부까지 같이 진행.

            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedQueryParameter("type", checkType)
            urlBuilder.addEncodedQueryParameter("value", inputVal)

            val urlString = urlBuilder.build().toString()
            Log.d("완성된 주소", urlString)

            val request = Request.Builder().url(urlString).get().build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {


                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val josn = JSONObject(bodyString)
                    Log.d("JSON 응답", josn.toString())
                    handler?.onResponse(josn)

                }

            })
        }

        fun postRequestLogin(
            context: Context, id: String, pw: String, handler: JsonResponseHandler?
        ) {
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
                    Log.d("JSON 응답", josn.toString())
                    handler?.onResponse(josn)

                }

            })

        }

        fun putRequestSignUp(
            context: Context, email: String, pw: String, nick: String, handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nick)
                .build()

            val request = Request.Builder().url(urlString).put(formData)/*.header()*/.build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {


                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val josn = JSONObject(bodyString)
                    Log.d("JSON 응답", josn.toString())
                    handler?.onResponse(josn)

                }

            })

        }

        fun getRequestMyInfo(
            context: Context, handler: JsonResponseHandler?
        ) {

            val client = OkHttpClient()

//            GET방식은 어디로 갈지 주소 + 어떤 데이터를 보낼지 같이 표시됨. 주소를 만들때 데이터 첨부까지 같이 진행.

            val urlBuilder = "${BASE_URL}/user_info".toHttpUrlOrNull()!!.newBuilder()

//            urlBuilder.addEncodedQueryParameter("type", checkType)
//            urlBuilder.addEncodedQueryParameter("value", inputVal)

            val urlString = urlBuilder.build().toString()
            Log.d("완성된 주소", urlString)

            val request = Request.Builder().url(urlString).get()
                .header("X-Http-Token", ContextUtil.getUserToken(context)).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {


                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val josn = JSONObject(bodyString)
                    Log.d("JSON 응답", josn.toString())
                    handler?.onResponse(josn)

                }

            })
        }

    }

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }


}