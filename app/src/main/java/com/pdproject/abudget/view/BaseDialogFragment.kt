package com.pdproject.abudget.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pdproject.abudget.R
import com.taskworld.kxandroid.getScreenWidth
import com.taskworld.kxandroid.lazyObservable
import kotlinx.android.synthetic.main.dialog_header.view.*
import kotlin.properties.Delegates

abstract class BaseDialogFragment : DialogFragment() {

    enum class ButtonType {
        POSITIVE,
        NEGATIVE,
        NEUTRAL
    }

    var headerTitle = ""
        set(value) {
            if (value.isNotEmpty()) {
                vRoot.tvDialogTitle.visibility = View.VISIBLE
                vRoot.tvDialogTitle.setBackgroundColor(ContextCompat.getColor(context, R.color.teal400))
                vRoot.tvDialogTitle.text = value
            } else {
                vRoot.tvDialogTitle.visibility = View.GONE
            }
        }

    var minWidth: Int by Delegates.lazyObservable({ (getScreenWidth(activity) * 0.8f).toInt() }) { meta, oldWidth, newWidth ->
        if (oldWidth != newWidth && newWidth > 0) {
            vRoot.minimumWidth = newWidth
        }
    }

    lateinit var vRoot: View
    lateinit var vContent: View
    abstract val contentResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //handle savedInstanceState
        if (savedInstanceState != null) {
            handleSavedInstanceState(savedInstanceState)
        }

        //handle arguments
        val args = arguments;
        if (args != null) {
            handleArguments(args)
        }

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TWTheme_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vRoot = inflater.inflate(R.layout.base_dialog, container)
        vRoot.minimumWidth = minWidth
        val parent = vRoot.llDialog
        vContent = inflater.inflate(contentResId, parent, false)
        val index = parent.indexOfChild(vRoot.tvDialogTitle)
        parent.addView(vContent, index + 1)
        return vRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI(view)
    }

    private fun setButton(type: ButtonType, text: CharSequence, handler: (View) -> Unit) {
        vRoot.llButtonPanel.visibility = View.VISIBLE
        val button = when (type) {
            ButtonType.POSITIVE -> vRoot.buttonPositive
            ButtonType.NEGATIVE -> vRoot.buttonNegative
            ButtonType.NEUTRAL -> vRoot.buttonNeutral
        }
        button.text = text
        button.setOnClickListener(handler)
    }

    fun setPositiveButton(text: CharSequence, handler: (View) -> Unit) {
        setButton(ButtonType.POSITIVE, text, handler)
    }

    fun setNegativeButton(text: CharSequence, handler: (View) -> Unit) {
        setButton(ButtonType.NEGATIVE, text, handler)
    }

    fun setNeutralButton(text: CharSequence, handler: (View) -> Unit) {
        setButton(ButtonType.NEUTRAL, text, handler)
    }

    open fun handleSavedInstanceState(savedInstanceState: Bundle) {
    }

    open fun handleArguments(args: Bundle) {
    }

    open fun setUpUI(view: View) {
    }

}