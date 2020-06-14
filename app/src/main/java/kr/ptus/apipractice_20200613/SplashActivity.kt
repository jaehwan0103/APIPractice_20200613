package kr.ptus.apipractice_20200613

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity :BaseActivity() {


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

            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)

            finish()

        }, 3000)



    }
}
