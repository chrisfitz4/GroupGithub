package com.illicitintelligence.android.groupgithub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.illicitintelligence.android.groupgithub.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashFragment = SplashFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.expand_center,R.anim.collapse_center,R.anim.expand_center,R.anim.collapse_center)
            .addToBackStack(splashFragment.tag)
            .add(R.id.frame_layout,splashFragment)
            .commit()
    }
}
