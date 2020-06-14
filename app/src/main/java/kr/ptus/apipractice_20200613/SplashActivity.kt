package kr.ptus.apipractice_20200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kr.ptus.apipractice_20200613.util.ContextUtil
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {


    }

    override fun setValues() {

        val myHandler = Handler()
        myHandler.postDelayed({

            if (ContextUtil.isAutoLogin(mContext)) {
                if (ContextUtil.getUserToken(mContext) != "") {
                    ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(json: JSONObject) {
                            val code = json.getInt("code")

                            if (code == 200) {
                                val myIntent = Intent(mContext, MainActivity::class.java)
                                startActivity(myIntent)
                            } else {
                                val myIntent = Intent(mContext, LoginActivity::class.java)
                                startActivity(myIntent)
                            }
                        }

                    })
                } else {

                    val myIntent = Intent(mContext, LoginActivity::class.java)
                    startActivity(myIntent)
                }

            } else {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()
            }

        }, 3000)


    }
}
