package com.example.hard75tracker.ui.home

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hard75tracker.R
import com.example.hard75tracker.databinding.DialogCustomImageSelectionBinding
import com.example.hard75tracker.databinding.FragmentHomeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDate
import java.util.*
import kotlin.properties.Delegates

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var sharedPref:SharedPreferences
    var btncount=0
    private lateinit var datelist:MutableSet<String>

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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val currentDate = LocalDate.now().toString()
        val storedDate = sharedPref.getString("date", "")
        if(currentDate!=storedDate){
            for(i in buttonStates.indices){
                buttonStates[i]=false
            }
            btncount=0
        }else{




        for(i in buttonStates.indices){
                    buttonStates[i]=sharedPref.getBoolean("buttonStates$i",false)

                }
                btncount=sharedPref.getInt("btncount",0)
                datelist= sharedPref.getStringSet("datelist", mutableSetOf("")) as MutableSet<String>


                }

        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        if(btncount==6){
            binding.confirmButton.visibility=View.VISIBLE

        }
        else{
            binding.confirmButton.visibility=View.GONE
        }



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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mw=binding.wk1
        var mwc=buttonStates[0]

        mw.setOnClickListener{
            if(!mwc){

            anim(mw,mwc)
                mwc=true
                btncount++
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
                customImageSelectionDialog()
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
        var confirm=binding.confirmButton



    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun update(){
        val editor=sharedPref.edit()
        val currentDate = LocalDate.now().toString()

        editor.putInt("btncount",buttonStates.count{it})
        for(i in buttonStates.indices){
            editor.putBoolean("buttonStates$i",buttonStates[i])
        }
        editor.putString("date", currentDate)


        editor.apply()
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

    private fun customImageSelectionDialog() {
        val dialog= Dialog(requireContext())

        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.TIRAMISU) {
                Dexter.withContext(requireContext())
                    .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,

                        )
                    .withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                            report?.let {
                                // Here after all the permission are granted launch the CAMERA to capture an image.
                                if (report.areAllPermissionsGranted()) {

                                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                    startActivityForResult(intent, CAMERA)
                                }
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<PermissionRequest>?,
                            token: PermissionToken?
                        ) {
                            showRationalDialogForPermissions()
                        }
                    }).onSameThread()
                    .check()
            }else{
                Dexter.withContext(requireContext())
                    .withPermissions(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.CAMERA,

                        )
                    .withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                            report?.let {
                                // Here after all the permission are granted launch the CAMERA to capture an image.
                                if (report.areAllPermissionsGranted()) {

                                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                    startActivityForResult(intent, CAMERA)
                                }
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<PermissionRequest>?,
                            token: PermissionToken?
                        ) {
                            showRationalDialogForPermissions()
                        }
                    }).onSameThread()
                    .check()
            }
            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.TIRAMISU) {
                Dexter.withContext(requireContext())
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            val galleryIntent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )

                            startActivityForResult(galleryIntent, GALLERY)
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {
                            Toast.makeText(
                                requireContext(),
                                "You have denied the storage permission to select image.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest,
                            token: PermissionToken
                        ) {
                            showRationalDialogForPermissions()
                        }
                    })
                    .onSameThread()
                    .check()
            }else{
                Dexter.withContext(requireContext())
                    .withPermission(Manifest.permission.READ_MEDIA_IMAGES)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            val galleryIntent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )

                            startActivityForResult(galleryIntent, GALLERY)
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {
                            Toast.makeText(
                                requireContext(),
                                "You have denied the storage permission to select image.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest,
                            token: PermissionToken
                        ) {
                            showRationalDialogForPermissions()
                        }
                    })
                    .onSameThread()
                    .check()

            }
            dialog.dismiss()
        }

        //Start the dialog and display it on screen.
        dialog.show()
    }
    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(requireContext())
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", null,null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == GALLERY && resultCode == RESULT_OK)) {
            val imageUri = data?.data
            val bitmap = getBitmap(activity!!.contentResolver, imageUri)
            saveImageToInternalStorage(bitmap)
        }
        else if(requestCode== CAMERA && resultCode== RESULT_OK){
            val imageBitmap=data?.extras?.get("data") as Bitmap
            saveImageToInternalStorage(imageBitmap)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveImageToInternalStorage(bitmap: Bitmap) {
        val folder = File(activity!!.getExternalFilesDir(null), IMAGE_DIRECTORY)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val currentDate = LocalDate.now().toString()

        val file = File(folder, "${currentDate}.png")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val CAMERA = 1

        private const val GALLERY = 2
        private const val IMAGE_DIRECTORY="75HardImages"

    }


}
