package com.pdproject.abudget.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.kittinunf.reactiveandroid.support.v7.widget.rx_itemsWith
import com.pdproject.abudget.R
import com.pdproject.abudget.model.Budget
import com.pdproject.abudget.viewModel.AllBudgetsViewModel
import com.taskworld.kxandroid.unSafeLazy
import kotlinx.android.synthetic.main.activity_all_budgets.*
import kotlinx.android.synthetic.main.item_budget.view.*

class AllBudgetsActivity : BaseActivity() {

    override val contentLayout = R.layout.activity_all_budgets
    val viewModel: AllBudgetsViewModel by unSafeLazy { AllBudgetsViewModel() }

    override fun setUpUI() {
        setUpList()
    }

    fun setUpList() {
        rvBudget.layoutManager = GridLayoutManager(this, 3)
        rvBudget.rx_itemsWith(
                observable = viewModel.budgets.takeUntil(lifecycle(LifecycleEvent.DESTROY)),
                onCreateViewHolder = { parent, viewType ->
                    val view = LayoutInflater.from(this).inflate(R.layout.item_budget, parent, false)
                    AllBudgetsViewHolder(view)
                },
                onBindViewHolder = { viewHolder, position, item: Budget ->
                    viewHolder.itemView.apply {
                        tvPrice.text = item.price.toString()
                    }
                })
    }

    //======================================
    // ViewHolder
    //======================================
    class AllBudgetsViewHolder(view: View) : RecyclerView.ViewHolder(view)

}