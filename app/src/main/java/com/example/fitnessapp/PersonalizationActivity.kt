package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.databinding.ActivityPersonalizationBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalizationActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPersonalizationBinding

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonalizationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java);


    }

    override fun onStart() {
        super.onStart()

        if(hasPersonalProgram())
        {
            navigateOut();
        }

    }

    private fun hasPersonalProgram(): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadIzbran(FirebaseAuth.getInstance().currentUser?.email.toString())


        }
        Thread.sleep(4000);
        if(firstFragmentViewModel.getIzbran().size==0)
        {
            return false;
        }
        else return true;
    }

    private fun navigateOut() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_personalization)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}