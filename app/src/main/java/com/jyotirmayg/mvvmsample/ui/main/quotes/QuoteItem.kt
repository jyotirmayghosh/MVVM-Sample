package com.jyotirmayg.mvvmsample.ui.main.quotes

import android.view.View
import com.jyotirmayg.mvvmsample.R
import com.jyotirmayg.mvvmsample.data.db.entities.Quote
import com.jyotirmayg.mvvmsample.databinding.ItemQuoteBinding
import com.xwray.groupie.viewbinding.BindableItem

class QuoteItem(private val quote: Quote) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout(): Int = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.quote = quote
    }

    override fun initializeViewBinding(view: View): ItemQuoteBinding {
        return ItemQuoteBinding.bind(view)
    }
}