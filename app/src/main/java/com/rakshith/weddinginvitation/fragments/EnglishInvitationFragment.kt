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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.utils.widget.ExitWithAnimation
import com.rakshith.weddinginvitation.utils.widget.startCircularReveal
import kotlinx.android.synthetic.main.fragment_invitation.*

class EnglishInvitationFragment(override var posX: Int?, override var posY: Int?) : BaseFragment(), ExitWithAnimation {
    override fun isToBeExitedWithAnimation(): Boolean = true
    var mediaPlayer: MediaPlayer? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invitation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.startCircularReveal(false)

        activity?.runOnUiThread {
            mediaPlayer = MediaPlayer.create(context, R.raw.english_quote_audio)
            mediaPlayer?.start()

            fragment_invitation_textWriter.setText("")
            fragment_invitation_textWriter.setCharacterDelay(60)
            fragment_invitation_textWriter.animateText(resources.getString(R.string.english_quote))
        }

        val spannableStringGreen = SpannableString(resources.getString(R.string.venue_green_zone))
        spannableStringGreen.setSpan(ForegroundColorSpan(resources.getColor(R.color.green)), 18, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
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
        spannableStringGreen.setSpan(clickableSpanGreen, 30, spannableStringGreen.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragment_invitation_tv_venue.text = spannableStringGreen
        fragment_invitation_tv_venue.movementMethod = LinkMovementMethod.getInstance()

        val spannableStringRed = SpannableString(resources.getString(R.string.venue_red_zone))
        spannableStringRed.setSpan(ForegroundColorSpan(resources.getColor(R.color.red)), 11, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val clickableSpanRed = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/3Relge1UxLQ"))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.limegreen)
            }
        }
        spannableStringRed.setSpan(clickableSpanRed, 28, spannableStringRed.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragment_invitation_tv_venue_red_zone.text = spannableStringRed
        fragment_invitation_tv_venue_red_zone.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }
}