package app.isfaaghyth.rxjetmovie.abstraction.base

abstract class UseCase<T> {
    abstract fun execute(): T
}