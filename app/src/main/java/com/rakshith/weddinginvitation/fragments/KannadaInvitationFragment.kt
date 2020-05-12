package com.rakshith.weddinginvitation.fragments

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.utils.YOUTUBE_LINK
import com.rakshith.weddinginvitation.utils.widget.ExitWithAnimation
import com.rakshith.weddinginvitation.utils.widget.startCircularReveal
import kotlinx.android.synthetic.main.fragment_invitation.*

class KannadaInvitationFragment(override var posX: Int?, override var posY: Int?) : BaseFragment(), ExitWithAnimation {
    override fun isToBeExitedWithAnimation(): Boolean = true
    var mediaPlayer: MediaPlayer? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invitation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.startCircularReveal(false)

        activity?.runOnUiThread {
            //            mediaPlayer = MediaPlayer.create(context, R.raw.english_quote_audio)
//            mediaPlayer?.start()

            fragment_invitation_textWriter.visibility = View.GONE
//            fragment_invitation_textWriter.setText("")
//            fragment_invitation_textWriter.setCharacterDelay(60)
//            fragment_invitation_textWriter.animateText(resources.getString(R.string.english_quote))
        }


        fragment_invitation_tv_name?.text = resources.getString(R.string.name_kn)
        fragment_invitation_tv_rakshith_parent_name?.text = resources.getString(R.string.parent_name_kn)
        fragment_invitation_tv_when?.text = resources.getString(R.string.when_kn)

        val spannableStringGreen = SpannableString(resources.getString(R.string.venue_green_zone_kn))
        spannableStringGreen.setSpan(ForegroundColorSpan(resources.getColor(R.color.green)), 0, spannableStringGreen.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val clickableSpanGreen = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=Lions Club (Community Hall), New town, Bhadravathi, Karnataka 577301"))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.limegreen)
            }
        }
        spannableStringGreen.setSpan(clickableSpanGreen, 32, spannableStringGreen.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragment_invitation_tv_venue.text = spannableStringGreen
        fragment_invitation_tv_venue.movementMethod = LinkMovementMethod.getInstance()

        Log.v("youtube link is ===", YOUTUBE_LINK)
        if (null != YOUTUBE_LINK && YOUTUBE_LINK?.isNotEmpty()!!) {
            Log.v("youtube link is ===", YOUTUBE_LINK)
            fragment_invitation_tv_venue_red_zone.visibility = View.VISIBLE

            val spannableStringRed = SpannableString(resources.getString(R.string.venue_red_zone_kn))
            spannableStringRed.setSpan(ForegroundColorSpan(resources.getColor(R.color.red)), 0, spannableStringRed.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            val clickableSpanRed = object : ClickableSpan() {
                override fun onClick(view: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_LINK))
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = resources.getColor(R.color.limegreen)
                }
            }
            spannableStringRed.setSpan(clickableSpanRed, 33, spannableStringRed.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            fragment_invitation_tv_venue_red_zone.text = spannableStringRed
            fragment_invitation_tv_venue_red_zone.movementMethod = LinkMovementMethod.getInstance()
        } else {
            Log.d("youtube link is ===", YOUTUBE_LINK)
            fragment_invitation_tv_venue_red_zone.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
//        mediaPlayer?.stop()
    }
}