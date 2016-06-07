package com.pdproject.abudget.viewModel

import com.pdproject.abudget.model.Budget
import com.taskworld.kxandroid.unSafeLazy
import rx.Observable

class AllBudgetsViewModel {

    val budgets: Observable<List<Budget>> by unSafeLazy {
        Observable.just(listOf(
                Budget(name = "Daily life", price = 2000f),
                Budget(name = "Transport", price = 1000f),
                Budget(name = "Shopping", price = 500f),
                Budget(name = "Utility", price = 200f))
        )
    }

}