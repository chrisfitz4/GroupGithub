package com.illicitintelligence.android.groupgithub.view

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.illicitintelligence.android.groupgithub.BuildConfig
import com.illicitintelligence.android.groupgithub.R
import com.illicitintelligence.android.groupgithub.network.OAuthenticationInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashFragment = SplashFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                0,
                R.anim.collapse_center,
                R.anim.expand_center,
                R.anim.collapse_center
            )
            .addToBackStack(splashFragment.tag)
            .add(R.id.frameRV, splashFragment)
            .commit()

        next_userbtn.setOnClickListener {
            val login = LoginFragment()
            supportFragmentManager.beginTransaction()
                .addToBackStack(login.tag)
                .add(R.id.frameRV, login)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()

        val oauth = OAuthenticationInstance()

        val uri : Uri? = this.intent.data

        if(uri.toString().startsWith(BuildConfig.clientCallback)) {
            val code : String? = uri?.getQueryParameter("code")


        }

    }
}
