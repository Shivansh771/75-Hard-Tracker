package com.example.hard75tracker.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hard75tracker.R
import com.example.hard75tracker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var sharedPref:SharedPreferences
    var btncount=0



    private val binding get() = _binding!!
    private val buttonStates=BooleanArray(6)


    override fun onPause() {
        super.onPause()

        super.onPause()
        val editor=sharedPref.edit()
        editor.putInt("btncount",buttonStates.count{it})
        for(i in buttonStates.indices){
            editor.putBoolean("buttonStates$i",buttonStates[i])
        }
        editor.apply()
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        btncount=sharedPref.getInt("btncount",0)
        for(i in buttonStates.indices){
            buttonStates[i]=sharedPref.getBoolean("buttonStates$i",false)

        }

        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        if(buttonStates[0]){
            binding.wk1.setBackgroundResource(R.drawable.shape_button_rounded)
        }
        if(buttonStates[1]){
            binding.wk2.setBackgroundResource(R.drawable.shape_button_rounded)

        }
        if(buttonStates[2]){
            binding.junk.setBackgroundResource(R.drawable.shape_button_rounded)
        }
        if(buttonStates[3]){
            binding.water.setBackgroundResource(R.drawable.shape_button_rounded)

        }
        if(buttonStates[4]){
            binding.pic.setBackgroundResource(R.drawable.shape_button_rounded)

        }
        if(buttonStates[5]){
            binding.book.setBackgroundResource(R.drawable.shape_button_rounded)
        }
        if(btncount>6){
            binding.confirmButton.visibility=View.VISIBLE
        }





        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mw=binding.wk1
        var mwc=buttonStates[0]

        mw.setOnClickListener{
            if(!mwc){

            anim(mw,mwc)
                mwc=true
                btncount++
                Toast.makeText(requireContext(),"$btncount",Toast.LENGTH_SHORT).show()
                buttonStates[0]=!buttonStates[0]

                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }

        }
        else{
            anim(mw,mwc)
            btncount--
            buttonStates[0]=!buttonStates[0]
            mwc=false
            btnunvis()


            }
        mwc=buttonStates[0]
        update()

        }
        var wk2=binding.wk2
        var wk2c=buttonStates[1]

        wk2.setOnClickListener{
            if(!wk2c){
                anim(wk2,wk2c)
                wk2c=true
                btncount++
                buttonStates[1]=!buttonStates[1]

                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }
            }
            else{
                anim(wk2,wk2c)
                btncount--
                wk2c=false
                btnunvis()
                buttonStates[1]=!buttonStates[1]


            }
            wk2c=buttonStates[1]

            update()}
        var jk=binding.junk
        var jkc=buttonStates[2]

        jk.setOnClickListener{
            if(!jkc){
                anim(jk,jkc)
                jkc=true
                btncount++
                buttonStates[2]=!buttonStates[2]

                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }
            }
            else{
                anim(jk,jkc)
                jkc=false
                btncount--
                buttonStates[2]=!buttonStates[2]

                btnunvis()

            }
            jkc=buttonStates[2]

            update()}
        var water=binding.water
        var wtrc=buttonStates[3]

        water.setOnClickListener{
            if(wtrc==false){
                anim(water,wtrc)
                btncount++
                wtrc=true
                buttonStates[3]=!buttonStates[3]

                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }
            }
            else{
                anim(water,wtrc)
                wtrc=false

                btncount--
                buttonStates[3]=!buttonStates[3]

                btnunvis()

            }
            wtrc=buttonStates[3]

            update()}
        var progress=binding.pic
        var picc=buttonStates[4]

        progress.setOnClickListener{
            if(picc==false){
                anim(progress,picc)
                btncount++
                picc=true
                buttonStates[4]=!buttonStates[4]

                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }
            }
            else{
                anim(progress,picc)
                btncount--
                picc=false
                buttonStates[4]=!buttonStates[4]

                btnunvis()

            }
            picc=buttonStates[4]

            update()}
        var book=binding.book
        var bookc=buttonStates[5]

        book.setOnClickListener{
            if(bookc==false){
                anim(book,bookc)
                btncount++
                buttonStates[5]=!buttonStates[5]

                bookc=true
                if(btncount==6){
                    val handler=Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        btnvis()
                    },200)

                }else{
                    btnunvis()
                }
            }
            else{
                book.setBackgroundResource(R.drawable.white_button)
                btncount--
                bookc=false
                buttonStates[5]=!buttonStates[5]

                btnunvis()
            }
            bookc=buttonStates[5]
        update()}



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
    fun btnvis(){
        binding.confirmButton.visibility=View.VISIBLE
    }
    fun btnunvis(){
        binding.confirmButton.visibility=View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun update(){
        val editor=sharedPref.edit()
        editor.putInt("btncount",buttonStates.count{it})
        for(i in buttonStates.indices){
            editor.putBoolean("buttonStates$i",buttonStates[i])
        }
        editor.apply()
    }
}