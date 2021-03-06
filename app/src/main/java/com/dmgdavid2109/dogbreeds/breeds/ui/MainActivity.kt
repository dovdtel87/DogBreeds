package com.dmgdavid2109.dogbreeds.breeds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.dmgdavid2109.dogbreeds.R
import com.dmgdavid2109.dogbreeds.di.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}
