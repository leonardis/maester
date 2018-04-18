package example.maester.base.mvp

interface BaseView {
    fun onError()
    fun setPresenter(presenter: BasePresenter<*>)
}