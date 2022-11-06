package com.mahmoud.zaher.fawrytask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahmoud.zaher.fawrytask.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}