package com.pdproject.abudget.viewModel

import com.github.kittinunf.reactiveandroid.MutableProperty
import com.pdproject.abudget.extension.filterNotNull
import com.pdproject.abudget.model.Budget
import com.taskworld.kxandroid.unSafeLazy
import rx.Observable

class AllBudgetsViewHolderViewModel() {

    val viewData by unSafeLazy { MutableProperty<Budget?>(null) }

    val name: Observable<String> = viewData.observable.filterNotNull().map { it.name }
    val income: Observable<Float> = viewData.observable.filterNotNull().map { it.income }
    val outcome = viewData.observable.filterNotNull().map { it.outcome }
    val percent = viewData.observable.filterNotNull().map {
        (it.outcome / it.income) * 100
    }

    fun bindTo(data: Budget) {
        viewData.value = data
    }
}
