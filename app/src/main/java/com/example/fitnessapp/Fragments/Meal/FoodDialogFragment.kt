package com.example.fitnessapp.Fragments.Meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_food_dialog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FoodDialogFragment(val addProductFragment: AddProductFragment) : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_food_dialog, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       var image= view.findViewById<ImageView>(R.id.imageView2)
        var naslov= view.findViewById<TextView>(R.id.textView7)
        var kalorii= view.findViewById<TextView>(R.id.textView9)
        kalorii.text=addProductFragment.product.calories + "cal. per 100g";
        naslov.text=addProductFragment.product.name;
        context?.let { Glide.with(it).load(addProductFragment.product.image).into(image) };


        addadd.setOnClickListener()
        {
            var grams=view.findViewById<EditText>(R.id.editTextTextPersonName)
            var izlez=(grams.text.toString().toDouble()/100)* addProductFragment.product.calories.toDouble()

            CoroutineScope(Dispatchers.IO).launch {
                addProductFragment.firstFragmentViewModel.loadwithProducts( addProductFragment.mAuth.currentUser?.email!!);

                addProductFragment.firstFragmentViewModel.updateMealUser(
                    (addProductFragment.firstFragmentViewModel.getwithProducts().playList.calories.toDouble()+izlez).toString(),
                    (addProductFragment.firstFragmentViewModel.getwithProducts().playList.caloriesRem.toDouble()-izlez).toString()
                    , addProductFragment.mAuth.currentUser?.email!!
                )

                withContext(Dispatchers.Main)
                {
                    addProductFragment.product.calories=izlez.toString();
                    addProductFragment.product.grams=grams.text.toString()
                    addProductFragment.firstFragmentViewModel.dodadiProduct(addProductFragment.product)
                    Thread.sleep(1000);

                    dismiss();

                    findNavController().navigate(R.id.action_addProductFragment2_to_caloriesDaily);
                }

            }
        }


    }


}