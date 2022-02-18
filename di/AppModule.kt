package zfani.assaf.fido.di

import zfani.assaf.fido.ui.articles.ArticlesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ArticlesListViewModel(get()) }
}

val fidoAppModules = listOf(
    appModule, networkModule, repositoryModule
)
