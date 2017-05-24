package `in`.nisb.nisbapp

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View

/**
 * Created by mridul on 19/3/17.
 */

internal class TabPagerAdapter(supportFragmentManager: FragmentManager, applicationContext: Context) : FragmentPagerAdapter(supportFragmentManager) {

    private val fragments = arrayOf("Home", "Events", "Blog", "Notifications")

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
            1 -> return EventFragment()
            2 -> return BlogFragment()
            3 -> return NotificationFragment()
            else -> return null
        }
    }

    override fun getPageTitle(position: Int): String {
        return ""//fragments[position];
    }

    override fun getCount(): Int {
        return fragments.size
    }


}
