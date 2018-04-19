package example.maester.base

import android.app.Application
import android.arch.persistence.room.Room
import example.maester.base.di.component.AppComponent
import example.maester.base.di.component.DaggerAppComponent
import example.maester.base.di.module.AppModule
import com.facebook.drawee.backends.pipeline.Fresco
import example.maester.utils.MaesterDatabase

class App: Application() {

    companion object{
       @JvmStatic lateinit var appComponent: AppComponent
        var database: MaesterDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        Fresco.initialize(this)
        App.database = Room.databaseBuilder(this, MaesterDatabase::class.java, "maester_db")
                .build()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}