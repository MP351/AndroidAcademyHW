package com.example.androidacademyhw

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


private const val PREFS_KEY = "SHARED_PREFS_KEY"
private const val COUNTER_KEY = "SHARED_PREFS_COUNTER_KEY"

class IntroScreenActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (needToShowIntro()) {
            setContentView(R.layout.activity_screen)
            val disposable = Completable.complete()
                .delay(3, TimeUnit.SECONDS)
                .subscribe(this::startSecActivity)
            compositeDisposable.add(disposable)
        } else {
            NewsListActivity.startActivity(this)
        }
    }

    private fun startSecActivity() {
        NewsListActivity.startActivity(this)
    }

    private fun needToShowIntro() : Boolean {
        val shPrefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        var counter = shPrefs.getInt(COUNTER_KEY, 1)
        shPrefs.edit().putInt(COUNTER_KEY, ++counter).apply()

        return counter % 2 == 0
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
