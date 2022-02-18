package zfani.assaf.fido.di

import zfani.assaf.fido.data.repository.article.ArticlesRepository
import zfani.assaf.fido.data.repository.article.ArticlesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}