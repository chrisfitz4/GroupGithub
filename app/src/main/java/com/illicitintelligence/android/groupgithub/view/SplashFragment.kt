package com.illicitintelligence.android.groupgithub.view

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.illicitintelligence.android.groupgithub.R
import kotlinx.android.synthetic.main.splash_layout.*

class SplashFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo: listen for when to set restart to false based on loading data in main activity
        var restart = true
        var count = 0
        val animatedVectorDrawableStart: AnimatedVectorDrawable = splash_animation.background as AnimatedVectorDrawable
        val animatedVectorDrawableTransition : AnimatedVectorDrawable = getDrawable(requireContext(),R.drawable.splash_anim_transition) as AnimatedVectorDrawable
        val animatedVectorDrawableEnd : AnimatedVectorDrawable = getDrawable(requireContext(), R.drawable.splash_anim_end) as AnimatedVectorDrawable

        //start the animation
        val animationCallback = object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                if(restart&&count<3){
                    animatedVectorDrawableStart.start()
                    count++
                    //todo: set restart to false here based on listener
                    Log.d("TAG_X",""+count)
                }else{
                    splash_animation.background = animatedVectorDrawableTransition
                    animatedVectorDrawableTransition.start()
                }
            }
        }
        animatedVectorDrawableStart.registerAnimationCallback(animationCallback)

        //transition to the next animation
        val animationCallbackTransition = object : Animatable2.AnimationCallback(){
            override fun onAnimationEnd(drawable: Drawable?) {
                splash_animation.background = animatedVectorDrawableEnd
                animatedVectorDrawableEnd.start()
            }
        }
        animatedVectorDrawableTransition.registerAnimationCallback(animationCallbackTransition)

        //finish the animation
        val animationCallbackEndSplash = object: Animatable2.AnimationCallback(){
            override fun onAnimationEnd(drawable: Drawable?) {
                fragmentManager?.popBackStack()
            }
        }
        animatedVectorDrawableEnd.registerAnimationCallback(animationCallbackEndSplash)

        animatedVectorDrawableStart.start()
    }
}