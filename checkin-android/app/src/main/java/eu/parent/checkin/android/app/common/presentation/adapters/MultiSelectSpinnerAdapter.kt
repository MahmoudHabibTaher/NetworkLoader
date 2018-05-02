package eu.parent.android.app.common.presentation.adapters

import android.content.Context
import android.support.v7.widget.AppCompatCheckBox
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import eu.parent.android.app.R


/**
 * Created by Raed Ezzat on 28/12/2017.
 */
class AdapterTagSpinnerItem(context: Context, private val listState: List<MultiSelectableItem>,
                            var onItemSelected: (Int) -> Unit = {}) : BaseAdapter() {
    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listState.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.layout_spinner_multi_select_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.mCheckBox.setOnCheckedChangeListener(null)
        vh.mCheckBox.isChecked = listState[position].selected
        vh.mCheckBox.text = listState[position].item
        vh.mCheckBox.setOnCheckedChangeListener { _, isChecked ->
            listState[position].selected = isChecked
            notifyDataSetChanged()
            onItemSelected(position)
        }

        return view
    }
}


private class ListRowHolder(row: View?) {
    val mCheckBox: AppCompatCheckBox = row?.findViewById(R.id.selected)!!
}


class MultiSelectableItem(
        var item: String = "",
        var selected: Boolean)