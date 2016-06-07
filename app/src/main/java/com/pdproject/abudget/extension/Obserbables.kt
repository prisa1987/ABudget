package com.pdproject.abudget.extension

import rx.Observable

fun <T> Observable<T?>.filterNotNull() = filter { it != null }.map { it!! }

fun Observable<Boolean>.not() = map { !it }

