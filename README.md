# maester

Maester es una aplicación Android desarrollada para la visualización básica de peliculas y series.

La data de la app fue proporcionada por: [The Movie Database API](http://themoviedb.org).

## Arquitectura

La arquitectura de la app estuvo basada en MVP con la utilización de las siguientes librerias:
* **Dagger2** para la inyección de dependencias.
* **Room** para la persistencia de datos
* **RxJava** para la programación reactiva
* **Retrofit** para el consumo de la api
* **Stetho** para el debug
* **Fresco** para la carga de imagenes
* **EventBus** para el manejo de eventos

## Capas de la app
### Modelo

La capa de modelo de la aplicación se planteo dentro del paquete llamado **models** y se utlizaron objetos de tipo *Data Class* junto a la creación de las entidades utilizadas por Room para la persistencia de datos.

**Clase de datos MoviesResult**
```
@Entity(tableName = "moviesresult")
data class MoviesResult(
        @PrimaryKey @SerializedName("id") val id: Int
)
```
### Vistas

La capa de vistas fue ejecutada mediante la utilización de *Interface* y *Activity/Fragments*, la interfaz era la encargada de generar el puente entre la interfaz de usuario y la capa de presentación.

**Interfaz BaseView**
```
interface BaseView {
    fun onError()
    fun setPresenter(presenter: BasePresenter<*>)
}
```
### Presentación

La capa de presentación fue la encargada de servir de puente entre los modelos y las interfaces de usuario; de igual manera se encargo de realizar la logica de negocio y las consultas a la API.

**Ejemplo de uso del presentador de peliculas MoviePresenter**
```
class MoviesPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MoviesView>(disposable, scheduler) {

    fun getPopulars() {
        view?.showProgress()
        Observable
                .concatArray(getMoviesFromDb(), getPopularsFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ populars ->
                    view?.hideProgress()
                    view?.onResponse(populars, 1)

                }, { _ ->
                    view?.hideProgress()
                    view?.onError()
                })
    }
}
```

### Red

La capa de comunicación de red fue usada mediante la libreria Retrofit, haciendo uso de una interfaz que contenia los endpoints que se consultan a traves de la aplicación.

**Interfaz Endpoints**
```
interface Endpoints {

    @GET("movie/popular?&api_key=$API_KEY&language=en-US&page=1")
    fun popularMovies(): Observable<MoviesListResponse>
}
```

*Cada una de las capas pueden verse con facilidad por la utilización adecuada de los nombres de los paquetes donde son contenidas*

## Responsabilidad de las clases

Basado en el primer principio **SOLID**, cada una de las clases se crearon con la intención de cumplir *una sola responsabilidad(**S**ingle responsability)* con el fin de generar un código limpio, legible, escalable y mantenible. A continuación podrá encontrar unos ejemplos de responsabilidad unica usados dentro de la aplicación.

**Responsabilidad de las actividades y fragmentos:** Cada una de las actividades y fragmentos de la aplicación se creo con el fin de mostrar al usuario los elementos de interfaz grafica para que el mismo pudiese interactuar con ellos.

**Responsabilidad de la lógica de negocio:** Como se pudo notar más arriba, la lógica de negocio fue delegada a los presentadores quienes son los encargados de manejar los eventos producidos desde las vistas a los datos de la aplicación.

## Caracteristicas de un código limpio

A traves del código se podrán apreciar diversas muestras de codigo limpio. Como anteriormente comente, la aplicación se baso en los principios **SOLID** y arquitectura **MVP** para lograr un buen código que pueda ser facilmente legible por diversos programadores y facilite su entendimiento.
