package com.pdproject.abudget.extension

import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView

fun TextView.setTextWithStyle(ch: CharSequence, style: Int) {
    val span = SpannableString(ch)
    span.setSpan(StyleSpan(style), 0, ch.length, 0)
    text = span
}
