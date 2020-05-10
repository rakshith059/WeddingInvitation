package com.rakshith.weddinginvitation.activities

import android.os.Bundle
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.fragments.HomeFragment
import com.rakshith.weddinginvitation.utils.AnimStyleEnum
import com.rakshith.weddinginvitation.utils.widget.ExitWithAnimation
import com.rakshith.weddinginvitation.utils.widget.exitCircularReveal

class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (isEmptyContainer())
            addFragment(HomeFragment(), TAG, AnimStyleEnum.SLIDE_RIGHT.name)
    }

    //used to check for the fragment in the home container
    private fun isEmptyContainer(): Boolean {
        val fragmentManager = supportFragmentManager
        return fragmentManager.findFragmentById(R.id.home_container) == null
    }

    override fun onBackPressed() {
        with(supportFragmentManager.findFragmentById(R.id.home_container)) {
            // Check if the current fragment implements the [ExitWithAnimation] interface or not
            // Also check if the [ExitWithAnimation.isToBeExitedWithAnimation] is `true` or not
            if ((this as? ExitWithAnimation)?.isToBeExitedWithAnimation() == true) {
                if (this.posX == null || this.posY == null) {
                    super.onBackPressed()
                } else {
                    this.view?.exitCircularReveal(this.posX!!, this.posY!!) {
                        super.onBackPressed()
                    } ?: super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }
}
