package com.pdproject.abudget.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.subjects.BehaviorSubject

abstract class BaseActivity : AppCompatActivity() {

    abstract val contentLayout: Int
    val lifecycleSubject = BehaviorSubject.create<LifecycleEvent>()

    enum class LifecycleEvent {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(LifecycleEvent.CREATE)

        if (contentLayout != 0) {
            setContentView(contentLayout)
        }

        savedInstanceState?.let { handleSavedInstanceState(it) }
        intent?.let { handleInten(it) }
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(LifecycleEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(LifecycleEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(LifecycleEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY)
        super.onDestroy()
    }

    fun lifecycle(event: LifecycleEvent) = lifecycleSubject.filter { it == event }

    //=====================================================
    // Initializer
    //=====================================================
    open fun handleSavedInstanceState(savedInstanceState: Bundle) {
    }

    open fun handleInten(intent: Intent) {
    }

    open fun setUpUI() {
    }

}
