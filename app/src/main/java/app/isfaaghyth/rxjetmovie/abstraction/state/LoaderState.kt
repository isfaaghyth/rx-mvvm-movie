package app.isfaaghyth.rxjetmovie.abstraction.state

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}