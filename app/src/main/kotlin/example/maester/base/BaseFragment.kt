package example.maester.base

import android.os.Bundle
import android.support.v4.app.Fragment
import example.maester.base.di.component.AppComponent
import example.maester.base.event.DefaultEvent
import example.maester.base.mvp.BasePresenter
import example.maester.base.mvp.BaseView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment : Fragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentInject()
    }

    protected abstract fun onFragmentInject()

    fun getAppcomponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onError() {
        toast("Something went wrong")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun defaultSubscribe(event: DefaultEvent){}
}