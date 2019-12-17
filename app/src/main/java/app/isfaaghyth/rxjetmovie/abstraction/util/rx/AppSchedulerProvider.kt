package app.isfaaghyth.rxjetmovie.abstraction.util.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()
    override fun ui(): Scheduler = Schedulers.newThread()
}