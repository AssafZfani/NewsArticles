package zfani.assaf.fido.ui.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import zfani.assaf.fido.R
import zfani.assaf.fido.databinding.FragmentArticleBinding
import zfani.assaf.fido.utils.viewBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val binding by viewBinding(FragmentArticleBinding::bind)
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() = with(binding) {
        args.article?.let {
            articleTitleTv.text = it.title
            articleContentTv.text = it.content
            articlePublishedAtTv.text = it.publishedAt
            articleLinkTv.text = it.url
            Glide.with(root.context).load(it.urlToImage).error(R.drawable.ic_article).into(articleImageIv)
        }
    }
}