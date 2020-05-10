package com.rakshith.weddinginvitation.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.adapters.ImageSlideShowAdapter
import com.rakshith.weddinginvitation.utils.AnimStyleEnum
import com.rakshith.weddinginvitation.utils.SLIDER_TIME_DELAY
import com.rakshith.weddinginvitation.utils.widget.findLocationOfCenterOnTheScreen
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment(), View.OnClickListener {
    val TAG = HomeFragment::class.java.simpleName
    var mediaPlayer: MediaPlayer? = null
    var isFabClosed = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fab_english.visibility = View.GONE
        fab_kannada.visibility = View.GONE

        fragment_home_vp_image.adapter = ImageSlideShowAdapter(rakshanaImageList())
        var currentPage = 0
        val handler = Handler()
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (currentPage == rakshanaImageList().size)
                        currentPage = 0

                    if (null != fragment_home_vp_image)
                        fragment_home_vp_image.setCurrentItem(currentPage++, true)
                }
            }
        }, SLIDER_TIME_DELAY, SLIDER_TIME_DELAY)

        activity?.runOnUiThread {
            fragment_home_textWriter.setText("")
            fragment_home_textWriter.setCharacterDelay(150)
            fragment_home_textWriter.animateText(resources.getString(R.string.marriage_date))
        }

        fab.setOnClickListener(this)
        fab_english.setOnClickListener(this)
        fab_kannada.setOnClickListener(this)
//        fragment_home_heartbeat.toggle()
//        fragment_home_heartbeat.setDurationBasedOnBPM(60)
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer.create(context, R.raw.home_background_audio)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }

    private fun rakshanaImageList(): ArrayList<Int> {
        val rakshanaImageList = ArrayList<Int>()
        rakshanaImageList.add(R.drawable.rakshana1)
        rakshanaImageList.add(R.drawable.rakshana2)
        rakshanaImageList.add(R.drawable.rakshana3)
        return rakshanaImageList
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> {
                if (isFabClosed) {
                    isFabClosed = false
                    fab_english.visibility = View.VISIBLE
                    fab_kannada.visibility = View.VISIBLE
                } else {
                    isFabClosed = true
                    fab_english.visibility = View.GONE
                    fab_kannada.visibility = View.GONE
                }
            }
            R.id.fab_english -> {
                val posX = view.findLocationOfCenterOnTheScreen().get(0)
                val posY = view.findLocationOfCenterOnTheScreen().get(1)
                fragmentCallback?.replaceFragment(EnglishInvitationFragment(posX, posY), TAG, AnimStyleEnum.SLIDE_UP.name)
            }
            R.id.fab_kannada -> {
                val posX = view.findLocationOfCenterOnTheScreen().get(0)
                val posY = view.findLocationOfCenterOnTheScreen().get(1)
                fragmentCallback?.replaceFragment(EnglishInvitationFragment(posX, posY), TAG, AnimStyleEnum.SLIDE_UP.name)
            }
        }
    }
}