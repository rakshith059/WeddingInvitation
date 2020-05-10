package com.rakshith.weddinginvitation.utils

import androidx.fragment.app.Fragment

interface IFragmentCallBack {

    /**
     * function for adding a fragment to the activity state
     */
    fun addFragment(fragment: Fragment, backStack: String?, animationStyle: String)

    /**
     * function for replace an existing fragment that was added to a container
     */
    fun replaceFragment(fragment: Fragment, backStack: String?, animationStyle: String)
}