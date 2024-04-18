package com.conscensia.draganddrop.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.conscensia.draganddrop.R
import com.conscensia.draganddrop.ui.draganddrop.DragAndDropFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DragAndDropFragment.newInstance())
                .commitNow()
        }
    }
}