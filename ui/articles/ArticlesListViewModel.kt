package zfani.assaf.fido.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import zfani.assaf.fido.data.repository.article.ArticlesRepository
import zfani.assaf.fido.data.events.articles.ArticlesEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class ArticlesListViewModel(private val articlesRepository: ArticlesRepository) : ViewModel() {

    val articlesChannel = Channel<ArticlesEvent>()

    fun getArticles() = viewModelScope.launch(Dispatchers.IO) {
        articlesChannel.send(ArticlesEvent.Loading)
        articlesChannel.send(articlesRepository.getArticles())
    }
}