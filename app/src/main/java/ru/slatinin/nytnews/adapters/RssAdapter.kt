package ru.slatinin.nytnews.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.slatinin.nytnews.data.news.MostPopularResult
import ru.slatinin.nytnews.databinding.NewsListItemBinding
import ru.slatinin.nytnews.data.RssReader
import ru.slatinin.nytnews.databinding.ItemRtRssBinding

class RssAdapter :
    PagingDataAdapter<RssReader.Item, RecyclerView.ViewHolder>(RssDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as RtRssViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RtRssViewHolder(
            ItemRtRssBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class RtRssViewHolder(
        private val binding: ItemRtRssBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.rssItem?.let { rssItem ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(rssItem.link))
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
            }
        }

        fun bind(item: RssReader.Item?) {
            binding.apply {
                rssItem = item
                executePendingBindings()
            }
        }
    }

    private class RssDiffCallback : DiffUtil.ItemCallback<RssReader.Item>() {

        override fun areItemsTheSame(old: RssReader.Item, aNew: RssReader.Item): Boolean {

            return old.title == aNew.title
        }

        override fun areContentsTheSame(
            old: RssReader.Item,
            aNew: RssReader.Item
        ): Boolean {
            return old.equals(aNew)
        }
    }
}