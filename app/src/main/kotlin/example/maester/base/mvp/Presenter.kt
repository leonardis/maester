package example.maester.base.mvp

interface Presenter<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}