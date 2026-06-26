package com.example.gymtrackergod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gymtrackergod.data.`1`.database.DatabaseInitializer
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.fragment.HomeFragment
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    HomeFragment()
                )
                .commit()
        }
        val database =
            DatabaseProvider.getDatabase(this)

        lifecycleScope.launch {

            DatabaseInitializer.initialize(database)

        }
    }
}