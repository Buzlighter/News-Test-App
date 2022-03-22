package com.test.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.test.newsapp.R
import androidx.fragment.app.add

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<NewsFragment>(R.id.fragment_main_container)
                setReorderingAllowed(true)
            }
        }
    }
}