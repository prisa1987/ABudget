package com.pdproject.abudget.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.kittinunf.reactiveandroid.rx.bindTo
import com.github.kittinunf.reactiveandroid.support.v7.widget.rx_itemsWith
import com.github.kittinunf.reactiveandroid.widget.rx_text
import com.pdproject.abudget.R
import com.pdproject.abudget.model.Budget
import com.pdproject.abudget.viewModel.AllBudgetsViewHolderViewModel
import com.pdproject.abudget.viewModel.AllBudgetsViewModel
import com.taskworld.kxandroid.alert
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
                    viewHolder.viewModel.bindTo(item)
                    viewHolder.setItem()
                })
    }

    //======================================
    // Create Budget
    //======================================
    fun addBudget() {
    }

    //======================================
    // ViewHolder
    //======================================
    class AllBudgetsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val viewModel by lazy(LazyThreadSafetyMode.NONE) { AllBudgetsViewHolderViewModel() }

        fun setItem() {
            viewModel.name.bindTo(view.tvTitle.rx_text)
            viewModel.income.map { it.toString() }.bindTo(view.tvPrice.rx_text)
            viewModel.percent.map { "${String.format("%.2f", it)} %" }.bindTo(view.tvPercent.rx_text)
        }

    }
}