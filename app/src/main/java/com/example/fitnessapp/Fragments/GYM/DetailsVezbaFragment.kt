package com.example.fitnessapp.Fragments.GYM

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import pl.droidsonroids.gif.GifImageView


class DetailsVezbaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_details_vezba, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name= arguments?.getString("name")
        var gif= arguments?.getString("gif")
        var target= arguments?.getString("target")
        var bodyPart= arguments?.getString("bodyPart")

        var gifImage=view.findViewById<GifImageView>(R.id.gifDetails)



        Glide.with(this).load(gif).into(gifImage);


        var gifName=view.findViewById<TextView>(R.id.nameGifDetails)
        gifName.text=name;
        var giffTarget=view.findViewById<TextView>(R.id.targetGifDetails)
        giffTarget.text="Target: "+target;
        var bodyPartDetails=view.findViewById<TextView>(R.id.bodyPartGifDetails)
        bodyPartDetails.text="Body part: "+bodyPart;

        var seconds=view.findViewById<TextView>(R.id.secondsS)
        seconds.setOnClickListener()
        {



                val countDownTimer = object : CountDownTimer(60000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {

                        seconds.text= (millisUntilFinished / 1000).toString()

                    }

                    override fun onFinish() {
                        seconds.text=  ("DONE!");


                        val countDownTimer = object : CountDownTimer(3000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {


                            }

                            override fun onFinish() {
                                seconds.text=  ("START AGAIN!");


                            }
                        }

                        countDownTimer.start()


                    }
                }

                countDownTimer.start()

        }





    }

}