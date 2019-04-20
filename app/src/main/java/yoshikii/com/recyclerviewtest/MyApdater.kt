package yoshikii.com.recyclerviewtest

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
}

class MyAdapter(
    context: Context, data: MutableList<String>
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    init {
        val list: MutableList<Any> = mutableListOf()

        list.add(HeaderData(title = "headerだよ"))
        if (data.any { it == "yoshikii" }) {
            list.add(ContentData(content = "yoshikiiだよ"))
        }
        list.add(FooterData(text = "footerだよ"))
        submitList(list)
    }

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HeaderData -> R.layout.view_header
            is ContentData -> R.layout.view_content
            is FooterData -> R.layout.view_footer
            else -> throw AssertionError()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.view_header -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.view_header, parent, false))
            }
            R.layout.view_content -> {
                ContentViewHolder(layoutInflater.inflate(R.layout.view_content, parent, false))
            }
            R.layout.view_footer -> {
                FooterViewHolder(layoutInflater.inflate(R.layout.view_footer, parent, false))
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            viewHolder is HeaderViewHolder && item is HeaderData -> {
                viewHolder.bind(item)
            }
            viewHolder is ContentViewHolder && item is ContentData -> {
                viewHolder.bind(item)
            }
            viewHolder is FooterViewHolder && item is FooterData -> {
                viewHolder.bind(item)
            }
        }
    }

    private class HeaderData(
        val title: String
    )

    private inner class HeaderViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val header: TextView = view.findViewById(R.id.header)

        fun bind(data: HeaderData) {
            header.text = data.title
        }
    }

    private class ContentData(
        val content: String
    )

    private inner class ContentViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.content)

        fun bind(data: ContentData) {
            content.text = data.content
        }
    }

    private class FooterData(
        val text: String
    )

    private inner class FooterViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val footer: TextView = view.findViewById(R.id.footer)

        fun bind(data: FooterData) {
            footer.text = data.text
        }
    }
}