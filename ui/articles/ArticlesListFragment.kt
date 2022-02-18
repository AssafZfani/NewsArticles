package zfani.assaf.fido.ui.articles

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import zfani.assaf.fido.R
import zfani.assaf.fido.data.events.articles.ArticlesEvent
import zfani.assaf.fido.data.network.entity.responses.Article
import zfani.assaf.fido.data.network.entity.responses.ErrorResponseApp
import zfani.assaf.fido.databinding.FragmentArticlesListBinding
import zfani.assaf.fido.ui.articles.adapter.ArticlesAdapter
import zfani.assaf.fido.utils.viewBinding

class ArticlesListFragment : Fragment(R.layout.fragment_articles_list) {

    private val binding by viewBinding(FragmentArticlesListBinding::bind)
    private val viewModel by viewModel<ArticlesListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getArticles()
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            for (event in viewModel.articlesChannel) {
                hideLoading()
                when (event) {
                    is ArticlesEvent.Loading -> showLoading()
                    is ArticlesEvent.SuccessArticles -> updateUI(event.articles)
                    is ArticlesEvent.Error -> showError(event.error) {
                        viewModel.getArticles()
                    }
                    is ArticlesEvent.UncheckedError -> toast(event.getMessage())
                }
            }
        }
    }

    private fun updateUI(articleList: List<Article>) = with(binding) {
        articlesRv.adapter = ArticlesAdapter().apply {
            setData(articleList)
            setOnItemSelectedListener {
                findNavController().navigate(
                    ArticlesListFragmentDirections.actionArticlesListFragmentToArticleFragment(it)
                )
            }
        }
    }

    private fun showLoading() = with(binding) {
        if (progressBar.isGone) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            progressBar.isVisible = true
        }
    }

    private fun hideLoading() = with(binding) {
        if (progressBar.isVisible) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            progressBar.isVisible = false
        }
    }

    private fun showError(error: ErrorResponseApp, tryAgainListener: (() -> Unit)) {
        context?.let {
            AlertDialog.Builder(it)
                .setCancelable(false)
                .setTitle(error.title)
                .setMessage(error.message)
                .setPositiveButton(error.button) { dialog, _ ->
                    dialog.dismiss()
                    if (error.internetError) tryAgainListener.invoke()
                }
                .create()
                .show()
        }
    }

    private fun toast(@StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}