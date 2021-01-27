package com.jyotirmayg.mvvmsample.ui.main.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jyotirmayg.mvvmsample.R
import com.jyotirmayg.mvvmsample.data.db.entities.Quote
import com.jyotirmayg.mvvmsample.util.Coroutines
import com.jyotirmayg.mvvmsample.util.hide
import com.jyotirmayg.mvvmsample.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactor by instance<QuotesViewModelFactor>()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {
        progress_circular.show()
        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(viewLifecycleOwner, Observer {
                progress_circular.hide()
                initRecyclerView(it.toQuoteItem())
            })
        }
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
        val mAdaptor = GroupAdapter<GroupieViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdaptor
        }
    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }

}