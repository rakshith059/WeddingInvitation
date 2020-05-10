package com.rakshith.weddinginvitation.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rakshith.weddinginvitation.R
import com.facebook.drawee.view.SimpleDraweeView

class SlideShowImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivSlideShowImage: SimpleDraweeView? = null
    private var tvSlideShowCaption: TextView? = null

    init {
        ivSlideShowImage = itemView.findViewById(R.id.slideshow_row_iv_image)
        tvSlideShowCaption = itemView.findViewById(R.id.slideshow_row_tv_caption)
    }

    fun bind(slideShowImage: Int) {
        ivSlideShowImage?.setImageResource(slideShowImage)
    }
}