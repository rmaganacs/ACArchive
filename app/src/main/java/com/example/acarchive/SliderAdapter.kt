package com.example.acarchive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.acarchive.databinding.SlideImageContainerBinding

class SliderAdapter(
    private val sliderItems: List<SliderItem>,
    private val viewPager2: ViewPager2,
    private val itemClickListener: MainActivity
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SlideImageContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position])
    }

    override fun getItemCount(): Int = sliderItems.size

    inner class SliderViewHolder(private val binding: SlideImageContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageSlide.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onSliderItemClick(position)
                }
            }
        }

        fun bind(sliderItem: SliderItem) {
            binding.imageSlide.setImageResource(sliderItem.image)
        }
    }

    interface OnSliderItemClickListener {
        fun onSliderItemClick(position: Int)
    }

}
