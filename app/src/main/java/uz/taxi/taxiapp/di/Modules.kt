package uz.taxi.taxiapp.di

import com.google.firebase.firestore.FirebaseFirestore
import uz.taxi.taxiapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.taxi.taxiapp.firebase.FirebaseManager

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseManager(get())}
//    single { FirebaseManager(get()) }

//    single { AuthManager(get()) }
//    single { FirebaseAuth.getInstance() }
//    single { FirebaseFirestore.getInstance() }
//    single { FirebaseManager(get()) }
//    single { Setting(get()) }
}

val viewModules = module {
    viewModel { MainViewModel(get()) }
//    viewModel { MainViewModel(get()) }
//    viewModel { LoginViewModel(get()) }
//    viewModel { MainViewModel(get()) }
}