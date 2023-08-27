package com.example.hard75tracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hard75tracker.R
import com.example.hard75tracker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mw=binding.wk1
        var mwc=false
        mw.setOnClickListener{
            if(!mwc){

            anim(mw,mwc)
                mwc=true
        }
        else{
            anim(mw,mwc)

            mwc=false
            }}
        var wk2=binding.wk2
        var wk2c=false
        wk2.setOnClickListener{
            if(!wk2c){
                anim(wk2,wk2c)
                wk2c=true
            }
            else{
                anim(wk2,wk2c)

                wk2c=false
            }}
        var jk=binding.junk
        var jkc=false
        jk.setOnClickListener{
            if(!jkc){
                anim(jk,jkc)
                jkc=true
            }
            else{
                anim(jk,jkc)
                jkc=false
            }}
        var water=binding.water
        var wtrc=false
        water.setOnClickListener{
            if(wtrc==false){
                anim(water,wtrc)
                wtrc=true
            }
            else{
                anim(water,wtrc)
                wtrc=false
            }}
        var progress=binding.pic
        var picc=false
        progress.setOnClickListener{
            if(picc==false){
                anim(progress,picc)
                picc=true
            }
            else{
                anim(progress,picc)
                picc=false
            }}
        var book=binding.book
        var bookc=false
        book.setOnClickListener{
            if(bookc==false){
                anim(book,bookc)
                bookc=true
            }
            else{
                book.setBackgroundResource(R.drawable.white_button)

                bookc=false
            }}

    }
    fun anim(btn:Button,bl:Boolean){
        val animn=AnimationUtils.loadAnimation(context,R.anim.fade)
        animn.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                if(!bl){
                    btn.setBackgroundResource(R.drawable.shape_button_rounded)
                }
                else{
                    btn.setBackgroundResource(R.drawable.white_button)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        btn.startAnimation(animn)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}