package kr.ptus.apipractice_20200613

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(
                mContext,
                inputEmail,
                inputPw,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        Log.d("화면에서 보는 응답", json.toString())

                        val codeNum = json.getInt("code")

                        if (codeNum == 200) {

                        } else {
                            val message = json.getString("message")
                            runOnUiThread {
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }

                        }

                    }

                })

        }

    }

    override fun setValues() {

    }
}
