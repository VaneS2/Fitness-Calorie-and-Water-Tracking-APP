package com.example.fitnessapp.Fragments.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import android.text.style.UnderlineSpan

import android.text.SpannableString





class NewContentView : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var fragment: NewsFragment = NewsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_new_content_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var title= arguments?.getString("title")
        var content= arguments?.getString("content")
        var description= arguments?.getString("description")
        var image_url= arguments?.getString("image_url")
        var link= arguments?.getString("link")

        view.findViewById<TextView>(R.id.NewTitleee).text=title;

        val aa = SpannableString(title)
        aa.setSpan(UnderlineSpan(), 0, aa.length, 0)
        view.findViewById<TextView>(R.id.NewTitleee).text=aa
        view.findViewById<TextView>(R.id.NewTitleee).setOnClickListener()
        {
            val webIntent: Intent = Uri.parse(link).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
        view.findViewById<TextView>(R.id.contentNeww).text=content;
     var slika=   view.findViewById<ImageView>(R.id.ImageNews)

        if(image_url==null)
        {
            Glide.with(this).load("https://freeiconshop.com/wp-content/uploads/edd/image-solid.png").into(slika);
        }
        else
        {
            Glide.with(this).load(image_url).into(slika);
        }
    }


}