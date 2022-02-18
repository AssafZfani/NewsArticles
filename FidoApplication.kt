package zfani.assaf.fido

import android.app.Application
import zfani.assaf.fido.di.fidoAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FidoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(fidoAppModules)
        }
    }
}