package com.shankar.shankarimagepicker

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ImagePickerComponentHolder
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.imageloader.DefaultImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val imageList = arrayListOf<com.esafirm.imagepicker.model.Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_pick_image.setOnClickListener { start() }

    }

    private val imagePicker: ImagePicker
        get() {
            val imagePicker = ImagePicker.create(this)
                    .language("in") // Set image picker language
                    .theme(R.style.ImagePickerTheme)
                    .toolbarArrowColor(Color.RED) // set toolbar arrow up color
                    .toolbarFolderTitle("Folder") // folder selection title
                    .toolbarImageTitle("Tap to select") // image selection title
                    .toolbarDoneButtonText("DONE") // done button text
                    .limit(10)
                    .imageDirectory("scrrenshots")
            DefaultImageLoader()

            return imagePicker.imageFullDirectory(Environment.getExternalStorageDirectory().path) // can be full path
        }
    private fun start() {

        imagePicker.start()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            imageList.clear()
            imageList.addAll(ImagePicker.getImages(data))
            printImages(imageList)
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun printImages(images: List<com.esafirm.imagepicker.model.Image>?) {
        if (images == null) return
        text_view.text = images.joinToString("\n")
        text_view.setOnClickListener {
            ImageViewerActivity.start(this@MainActivity, images)

        }
    }

}

