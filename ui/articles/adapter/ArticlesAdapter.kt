package zfani.assaf.fido.ui.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import zfani.assaf.fido.R
import zfani.assaf.fido.data.network.entity.responses.Article
import zfani.assaf.fido.databinding.ItemListArticleBinding
import zfani.assaf.fido.ui.articles.adapter.diffutils.ArticleDiffUtils

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var data = listOf<Article>()
    private var onItemSelectedListener: ((article: Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .let { ItemListArticleBinding.inflate(it, parent, false) }
            .let { ArticleViewHolder(it) }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setData(articleList: List<Article>) {
        val articleDiffUtilCallback = ArticleDiffUtils(data, articleList)
        val articleDiffResult = DiffUtil.calculateDiff(articleDiffUtilCallback)
        data = articleList
        articleDiffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemSelectedListener(onItemSelectedListener: ((article: Article) -> Unit)?) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    inner class ArticleViewHolder(private val binding: ItemListArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) = with(binding) {
            setTextOrHide(articleTitleTv, item.title)
            setTextOrHide(articleDescriptionTv, item.description)
            setTextOrHide(articleAuthorTv, item.author)
            Glide.with(root.context).load(item.urlToImage)
                .error(R.drawable.ic_article).into(articleImageIv)
            root.setOnClickListener {
                onItemSelectedListener?.invoke(item)
            }
        }

        private fun setTextOrHide(textView: TextView, content: String?) = with(textView) {
            if (!content.isNullOrEmpty() && content != "null") {
                isVisible = true
                text = content
            } else {
                isVisible = false
            }
        }
    }
}