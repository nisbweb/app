package app.nisb.nisbapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nisb.nisbapp.R
import kotlinx.android.synthetic.main.fragment_home.*;
import kotlinx.android.synthetic.main.activity_main.*;

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_account.setOnClickListener({
            activity.main_pager.setCurrentItem(4)
            activity.main_tabs.setSelectedNavigationItem(4)
        })
        home_notifs.setOnClickListener({
            activity.main_pager.setCurrentItem(3)
            activity.main_tabs.setSelectedNavigationItem(3)
        })
        home_blog.setOnClickListener({
            activity.main_pager.setCurrentItem(2)
            activity.main_tabs.setSelectedNavigationItem(2)
        })
        home_events.setOnClickListener({
            activity.main_pager.setCurrentItem(1)
            activity.main_tabs.setSelectedNavigationItem(1)
        })

    }

    public fun newInstance(text: String): HomeFragment {

        val f = HomeFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }

}// Required empty public constructor
