package com.rakshith.weddinginvitation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rakshith.weddinginvitation.R
import com.rakshith.weddinginvitation.utils.AnimStyleEnum
import com.rakshith.weddinginvitation.utils.IFragmentCallBack

abstract class BaseActivity : AppCompatActivity(), IFragmentCallBack {
    var mActivity: AppCompatActivity? = null
    var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
    }

    /**
     * function for adding a fragment to the activity state
     */
    override fun addFragment(fragment: Fragment, backStack: String?, animationStyle: String) {
        try {
            if (null == mActivity)
                return

            val fragmentTransaction = addFragmentTransaction(fragment, backStack, animationStyle)
            fragmentTransaction.commit()
        } catch (illegalStateException: IllegalStateException) {
            illegalStateException.printStackTrace()
            val fragmentTransaction = addFragmentTransaction(fragment, backStack, animationStyle)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    /**
     * function for replace an existing fragment that was added to a container
     */
    override fun replaceFragment(fragment: Fragment, backStack: String?, animationStyle: String) {
        try {
            if (null == mActivity)
                return

            val fragmentTransaction = replaceFragmentTransaction(fragment, backStack, animationStyle)
            fragmentTransaction.commit()
        } catch (illegalStateException: IllegalStateException) {
            illegalStateException.printStackTrace()
            val fragmentTransaction = replaceFragmentTransaction(fragment, backStack, animationStyle)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    /**
     * Function adds the fragment to the container and returns fragmentTransaction
     */
    private fun addFragmentTransaction(fragment: Fragment, backStack: String?, animationStyle: String): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        setAnimation(animationStyle, fragmentTransaction)
        fragmentTransaction.add(R.id.home_container, fragment, backStack)

        /**
         * if backstack string is not null then add the fragment string to the stack
         */
        if (null != backStack) {
            fragmentTransaction.addToBackStack(backStack)
        }
        mFragment = fragment
        return fragmentTransaction
    }

    /**
     * Function replace the fragment to the container and returns fragmentTransaction
     */
    private fun replaceFragmentTransaction(fragment: Fragment, backStack: String?, animationStyle: String): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        setAnimation(animationStyle, fragmentTransaction)
        fragmentTransaction.replace(R.id.home_container, fragment, backStack)

        /**
         * if backstack string is not null then add the fragment string to the stack
         */
        if (null != backStack) {
            fragmentTransaction.addToBackStack(backStack)
        }
        mFragment = fragment
        return fragmentTransaction
    }

    /**
     * function to decide the animation
     */
    private fun setAnimation(animationStyle: String, fragmentTransaction: FragmentTransaction) {
        when (animationStyle) {
            AnimStyleEnum.SLIDE_LEFT.name -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_left_in_anim,
                R.anim.slide_left_out_anim,
                R.anim.slide_right_in_anim,
                R.anim.slide_right_out_anim
            )
            AnimStyleEnum.SLIDE_RIGHT.name -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_right_in_anim,
                R.anim.slide_right_out_anim,
                R.anim.slide_left_in_anim,
                R.anim.slide_left_out_anim
            )
            AnimStyleEnum.SLIDE_UP.name -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_up_in_anim,
                R.anim.slide_up_out_anim,
                R.anim.slide_down_in_anim,
                R.anim.slide_down_out
            )
            AnimStyleEnum.SLIDE_DOWN.name -> fragmentTransaction.setCustomAnimations(
                R.anim.slide_down_in_anim,
                R.anim.slide_down_out,
                R.anim.slide_up_in_anim,
                R.anim.slide_up_out_anim
            )
            else -> fragmentTransaction.setCustomAnimations(0, 0)
        }
    }

    override fun onBackPressed() {
        //check if there is only one fragment transaction in the backstack then quit the activity
        //otherwise popback one transaction
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}