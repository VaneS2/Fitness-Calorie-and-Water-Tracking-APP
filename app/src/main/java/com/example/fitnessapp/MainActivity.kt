package com.example.fitnessapp

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.Service.MyService
import com.example.fitnessapp.database.dao.FirstFragmentMealModel

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity()  {

    private lateinit var navController:NavController
    var startServicee=0;
    private lateinit var firstFragmentViewModel: FirstFragmentMealModel
    val timer = Timer()
    lateinit var  hourlyTask: TimerTask

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)
        navController = Navigation.findNavController(this,R.id.fragmentContainer)

        val bottom_navigation=findViewById<BottomNavigationView>(R.id.bottom_navigation)

        System.out.println(navController )

        bottom_navigation.setupWithNavController(navController)






        var topToolbar=  findViewById<Toolbar>(R.id.navigationView2)

        topToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.logoutt ->{
                    stopService(Intent(this,MyService::class.java))
                    var user: FirebaseAuth =FirebaseAuth.getInstance()
                    user.signOut();
                    val intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent);}

            }
            true
        }

    }



     fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }


    override fun onStart() {
        super.onStart()

        if(!checkIfLoggedIn()) {
            navigateOut()
        }



        if(checkIfLoggedIn()){
        if(!hasPersonalProgram())
        {
            makeOne()
        }
        if(hasPersonalProgram())
        {
            if(startServicee==0)
            {
                startService(Intent(this,MyService::class.java))
                startServicee=1;
            }

        }

        }
    }


    private fun checkIfLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    private fun navigateOut() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun hasPersonalProgram(): Boolean {

        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadIzbran(FirebaseAuth.getInstance().currentUser?.email.toString())

        }
        Thread.sleep(2000);
         if(firstFragmentViewModel.getIzbran().size==0)
         {
             return false;
         }
        else return true;
    }


    private fun makeOne() {
        val intent: Intent = Intent(this, PersonalizationActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}