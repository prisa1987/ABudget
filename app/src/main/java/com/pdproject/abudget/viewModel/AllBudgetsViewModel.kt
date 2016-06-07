package com.pdproject.abudget.viewModel

import com.pdproject.abudget.model.Budget
import com.taskworld.kxandroid.unSafeLazy
import rx.Observable

class AllBudgetsViewModel {

    val budgets: Observable<List<Budget>> by unSafeLazy {
        Observable.just(listOf(
                Budget(name = "Daily life", income = 2000f, outcome = 50f),
                Budget(name = "Transport", income = 1000f, outcome = 199f),
                Budget(name = "Shopping", income = 500f, outcome = 29f),
                Budget(name = "Utility", income = 200f, outcome = 29f))
        )
    }


}