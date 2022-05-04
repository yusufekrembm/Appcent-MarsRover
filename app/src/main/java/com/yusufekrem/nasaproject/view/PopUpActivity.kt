package com.yusufekrem.nasaproject.view

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.yusufekrem.nasaproject.R
import com.yusufekrem.nasaproject.model.Data
import com.yusufekrem.nasaproject.viewmodel.MarsViewModel
import kotlinx.android.synthetic.main.activity_pop_up.*


class PopUpActivity : AppCompatActivity() {

    private lateinit var marsViewModel : MarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        marsViewModel = ViewModelProviders.of(this).get(MarsViewModel::class.java)
        setContentView(R.layout.activity_pop_up)
        setUI()
    }
    @SuppressLint("SetTextI18n")
    private fun setUI() {
        marsViewModel.data.observe(this, Observer { data ->

            val extras = intent.extras
            if (extras != null) {
                val id = extras.getString("id")

                var item: Data.Photo? = null
                for (elem in data.photos) {
                    if (elem.id == id?.toInt() ?: -1) {
                        item = elem
                    }
                }

                val title = findViewById<View>(R.id.popup_window_title) as TextView
                title.text = "ID :" +item?.id.toString()
                val date = findViewById<View>(R.id.popup_window_button4) as TextView
                date.text = "Date : " + item?.earth_date.toString()
                val roverName = findViewById<View>(R.id.popup_window_button6) as TextView
                roverName.text = "Rover Name : " + item?.rover!!.name
                val cameraName = findViewById<View>(R.id.popup_window_button2) as TextView
                cameraName.text = "Camera : " + item?.camera!!.name
                val roverStatus = findViewById<View>(R.id.popup_window_button5) as TextView
                roverStatus.text = "Status : " + item?.rover.status
                val roverLaunchDate = findViewById<View>(R.id.popup_window_button3) as TextView
                roverLaunchDate.text = "Launch Date : " + item?.rover.launch_date
                val roverLandingDate = findViewById<View>(R.id.popup_window_button) as TextView
                roverLandingDate.text = "Landing Date : " + item?.rover.landing_date
                val image = "${item?.img_src}".replace("http","https")
                Picasso.get().load(image).into(imageView2)



            }





        })
    }
}