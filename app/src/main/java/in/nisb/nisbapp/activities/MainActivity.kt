package `in`.nisb.nisbapp

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TabHost


import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var pager: ViewPager? = null

    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTabs()
    }


    fun setupTabs() {

        pager = findViewById(R.id.main_pager) as ViewPager
        tabLayout = findViewById(R.id.main_tablayout) as TabLayout

        pager!!.adapter = TabPagerAdapter(supportFragmentManager, applicationContext)

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout!!.setupWithViewPager(pager)
        tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.nisblogo24)
        tabLayout!!.getTabAt(1)!!.text = "Events" //.setIcon(R.drawable.event);
        tabLayout!!.getTabAt(2)!!.text = "Blog" //.setIcon(R.drawable.blog);
        tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.notification)
    }

    //Handle back Button
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        return super.onKeyDown(keyCode, event)
    }

}
