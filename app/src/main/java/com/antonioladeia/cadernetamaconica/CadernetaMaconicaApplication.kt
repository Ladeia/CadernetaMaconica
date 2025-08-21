package com.antonioladeia.cadernetamaconica

import android.app.Application
import com.antonioladeia.cadernetamaconica.data.AppContainer
import com.antonioladeia.cadernetamaconica.data.AppDataContainer

class CadernetaMaconicaApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
