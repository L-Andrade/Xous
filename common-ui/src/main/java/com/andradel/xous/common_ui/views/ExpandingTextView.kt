package com.andradel.xous.common_ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.transition.TransitionManager.beginDelayedTransition
import com.andradel.xous.common_ui.R
import kotlinx.android.synthetic.main.expanding_textview.view.*

class ExpandingTextView : LinearLayout {

    private var maxLines: Int = DEFAULT_MAX_LINES

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.expanding_textview, this, true)

        context.withStyledAttributes(attrs, R.styleable.ExpandingTextView) {
            maxLines = getInt(R.styleable.ExpandingTextView_maxLines, DEFAULT_MAX_LINES)
        }

        expandableText.maxLines = maxLines

        showMoreOrLess.setOnClickListener {
            beginDelayedTransition(this)
            expandableText.maxLines = if (expandableText.maxLines == maxLines) {
                showMoreOrLess.text = resources.getString(R.string.show_less)
                Int.MAX_VALUE
            } else {
                showMoreOrLess.text = resources.getString(R.string.show_more)
                maxLines
            }
        }
    }

    var text: String = ""
        set(value) {
            expandableText.text = value
            this.post {
                showMoreOrLess.isVisible = expandableText.isEllipsized
            }
            field = value
        }

    companion object {
        private const val DEFAULT_MAX_LINES = 4
    }
}

private val TextView.isEllipsized: Boolean
    get() = layout.getEllipsisCount(expandableText.lineCount - 1) > 0