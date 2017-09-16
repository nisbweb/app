package app.nisb.nisbapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nisb.nisbapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    public fun newInstance(text: String): HomeFragment {

        val f = HomeFragment()
        val b = Bundle()
        b.putString("msg", text)

        f.setArguments(b)

        return f
    }

}// Required empty public constructor
