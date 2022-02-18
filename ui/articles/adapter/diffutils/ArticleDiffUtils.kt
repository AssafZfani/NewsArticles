package zfani.assaf.fido.ui.articles.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import zfani.assaf.fido.data.network.entity.responses.Article

class ArticleDiffUtils(
    private val oldArticleList: List<Article>,
    private val newArticleList: List<Article>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldArticleList.size

    override fun getNewListSize(): Int = newArticleList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldArticleList[oldItemPosition].title == newArticleList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldArticleList[oldItemPosition].description == newArticleList[newItemPosition].description
}