package com.rakshith.weddinginvitation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.viewholders.SlideShowImageHolder


class ImageSlideShowAdapter(val rakshanaImageList: ArrayList<Int>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return rakshanaImageList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = LayoutInflater.from(container.context).inflate(R.layout.slideshow_row, container, false)
        val slideshowImageHolder = SlideShowImageHolder(view)

        slideshowImageHolder.bind(rakshanaImageList[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}