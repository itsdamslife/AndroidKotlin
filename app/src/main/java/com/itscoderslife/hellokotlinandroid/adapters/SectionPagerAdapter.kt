package com.itscoderslife.hellokotlinandroid.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.itscoderslife.hellokotlinandroid.fragments.ChatFragment
import com.itscoderslife.hellokotlinandroid.fragments.UsersFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(index: Int): Fragment {
        when(index) {
            0 ->
                return UsersFragment()
            1 ->
                return ChatFragment()
        }
        return null!!

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Friends"
            1 -> return "Chats"
        }
        return null
    }
}