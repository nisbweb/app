package app.nisb.nisbapp


import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import app.nisb.nisbapp.fragments.*
import it.neokree.materialtabs.MaterialTab
import it.neokree.materialtabs.MaterialTabListener

class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment().newInstance( "Page # 1")
            1 -> return EventFragment().newInstance("Page # 2")
            2 -> return BlogFragment().newInstance("Page # 3")
            3 -> return NotifsFragment().newInstance("Page # 4")
            4 -> return AccountFragment().newInstance("Page # 5")
            else -> return HomeFragment().newInstance("Page # 1")
        }
    }

}

class MainActivity : AppCompatActivity(), MaterialTabListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyAdapter(supportFragmentManager)
        main_pager.adapter = adapter

        main_tabs.addTab(main_tabs.newTab()
                .setIcon(resources.getDrawable(R.drawable.ic_home))
                .setTabListener(this))
        main_tabs.addTab(
                main_tabs.newTab()
                        .setIcon(resources.getDrawable(R.drawable.ic_event))
                        .setTabListener(this)
        )

        main_tabs.addTab(
                main_tabs.newTab()
                        .setIcon(resources.getDrawable(R.drawable.ic_blog))
                        .setTabListener(this)
        )

        main_tabs.addTab(
                main_tabs.newTab()
                        .setIcon(resources.getDrawable(R.drawable.ic_notification))
                        .setTabListener(this)
        )

        main_tabs.addTab(
                main_tabs.newTab()
                        .setIcon(resources.getDrawable(R.drawable.ic_account))
                        .setTabListener(this)
        )


        main_pager.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                // when user do a swipe the selected tab change
                main_tabs.setSelectedNavigationItem(position)
            }
        })
    }

    override fun onTabSelected(p0: MaterialTab) {
        main_pager.setCurrentItem(p0!!.position)
    }

    override fun onTabReselected(p0: MaterialTab?) {
        main_pager.setCurrentItem(p0!!.position)
    }

    override fun onTabUnselected(p0: MaterialTab?) {

    }
}
