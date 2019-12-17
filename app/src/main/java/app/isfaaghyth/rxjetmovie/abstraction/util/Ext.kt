package app.isfaaghyth.rxjetmovie.abstraction.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    factory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, factory).get(VM::class.java)

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions())
        .into(this)
}