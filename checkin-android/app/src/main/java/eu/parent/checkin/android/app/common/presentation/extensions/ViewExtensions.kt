package eu.parent.android.app.common.presentation.extensions

import android.content.Context
import android.support.annotation.ArrayRes
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.design.widget.TabLayout
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import eu.parent.android.app.R


/**
 * Created by mahmoud on 6/27/17.
 */

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(rootView?.windowToken, 0)
}

fun View.showMenuPopup(@MenuRes menuRes: Int, init: (PopupMenu) -> Unit = {}, onItemClick: (Int) -> Unit) {
    val popUpMenu = PopupMenu(context, this)
    popUpMenu.inflate(menuRes)
    init(popUpMenu)
    popUpMenu.setOnMenuItemClickListener {
        onItemClick(it.itemId)
        true
    }
    popUpMenu.show()
}

fun View.showMenuPopupWithIcon(@MenuRes menuRes: Int, init: (PopupMenu) -> Unit = {}, onItemClick: (Int) -> Unit) {
    val popUpMenu = PopupMenu(context, this)

    try {
        val fields = popUpMenu.javaClass.declaredFields
        for (field in fields) {
            if ("mPopup" == field.name) {
                field.isAccessible = true
                val menuPopupHelper = field.get(popUpMenu)
                val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                setForceIcons.invoke(menuPopupHelper, true)
                break
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    popUpMenu.menuInflater.inflate(menuRes, popUpMenu.menu)
    init(popUpMenu)
    popUpMenu.setOnMenuItemClickListener {
        onItemClick(it.itemId)
        true
    }
    popUpMenu.show()
}

fun <T> View.showSelectPopUp(items: List<T>,
                             initPopUp: (ListPopupWindow) -> Unit = {},
                             dismissOnClick: Boolean = true,
                             wrapWidth: Boolean = false,
                             onItemClick: (ListPopupWindow, T, Int) -> Unit = { _, _, _ -> }) {
    val stringsList = items.map { it.toString() }
    val adapter = ArrayAdapter<String>(context, R.layout.default_select_popup_item, stringsList)

    val popPup = ListPopupWindow(context)
    popPup.anchorView = this

    popPup.isModal = true

    popPup.height = ViewGroup.LayoutParams.WRAP_CONTENT
    popPup.width = ViewGroup.LayoutParams.WRAP_CONTENT

    popPup.promptPosition = ListPopupWindow.POSITION_PROMPT_BELOW

    if (wrapWidth) {
        popPup.setContentWidth(measureContentWidth(adapter, this, context))
    }

    initPopUp(popPup)

    popPup.setOnItemClickListener { _, _, position, _ ->
        val item = items[position]
        onItemClick(popPup, item, position)
        if (popPup.isShowing && dismissOnClick) {
            popPup.dismiss()
        }
    }
    popPup.setAdapter(adapter)
    popPup.show()
}

private fun measureContentWidth(adapter: ListAdapter, view: View, context: Context): Int {
    var measureParent: ViewGroup? = null
    var maxWidth = 0
    var itemView: View? = null
    var itemType = 0

    val widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
    val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

    val count = adapter.count
    for (i in 0 until count) {
        val positionType = adapter.getItemViewType(i)
        if (positionType != itemType) {
            itemType = positionType
            itemView = null
        }

        if (measureParent == null) {
            measureParent = FrameLayout(context)
        }

        itemView = adapter.getView(i, itemView, measureParent)
        itemView.measure(widthMeasureSpec, heightMeasureSpec)

        val itemWidth = itemView.measuredWidth

        if (itemWidth > maxWidth) {
            maxWidth = itemWidth
        }
    }

    if (view.width > maxWidth) {
        maxWidth = view.width
    }

    return maxWidth
}

fun Spinner.populateSpinner(@ArrayRes items: Int, @LayoutRes itemLayout: Int = android.R.layout.simple_spinner_item,
                            @LayoutRes dropDownItemLayout: Int = android.R.layout.simple_spinner_dropdown_item) {
    populateSpinner(resources.getStringArray(items), itemLayout, dropDownItemLayout)
}

fun Spinner.populateSpinner(items: Array<String>, @LayoutRes itemLayout: Int = android.R.layout.simple_spinner_item,
                            @LayoutRes dropDownItemLayout: Int = android.R.layout.simple_spinner_dropdown_item) {
    adapter = ArrayAdapter(context, itemLayout, items).apply {
        setDropDownViewResource(dropDownItemLayout)
    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun TabLayout.onTabSelected(onTabSelected: (TabLayout.Tab) -> Unit = {},
                            onTabReselected: (TabLayout.Tab) -> Unit = {},
                            onTabUnselected: (TabLayout.Tab) -> Unit = {}) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.apply(onTabSelected)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            tab?.apply(onTabReselected)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            tab?.apply(onTabUnselected)
        }
    })
}