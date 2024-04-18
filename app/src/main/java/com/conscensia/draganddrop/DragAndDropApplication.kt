package com.conscensia.draganddrop;

import android.app.Application;
import com.conscensia.draganddrop.di.appModule
import com.conscensia.draganddrop.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

public class DragAndDropApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DragAndDropApplication)
            modules(appModule, viewModelsModule)
        }
    }
}