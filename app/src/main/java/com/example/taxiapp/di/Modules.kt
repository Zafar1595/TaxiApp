package com.example.taxiapp.di

import com.example.taxiapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
//    single { FirebaseFirestore.getInstance() }
//    single { FirebaseManager(get()) }

//    single { AuthManager(get()) }
//    single { FirebaseAuth.getInstance() }
//    single { FirebaseFirestore.getInstance() }
//    single { FirebaseManager(get()) }
//    single { Setting(get()) }
}

val viewModules = module {
    viewModel { MainViewModel() }
//    viewModel { MainViewModel(get()) }
//    viewModel { LoginViewModel(get()) }
//    viewModel { MainViewModel(get()) }
}