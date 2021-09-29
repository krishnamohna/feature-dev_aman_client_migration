package com.cardio.doctor.ui.views.activity.tutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cardio.doctor.databinding.TutorialSlideLayoutBinding
import com.cardio.doctor.model.TutorialModel

class SliderAdapter(private val theContext: Context, private val tutorialModel: TutorialModel) : PagerAdapter(){
    override fun getCount(): Int {
        return tutorialModel.listOfImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(theContext)
        val binding = TutorialSlideLayoutBinding.inflate(inflater, container, false)
        binding.tutorialImage.setImageResource(tutorialModel.listOfImages[position])
        binding.tutorialTitle.text = tutorialModel.listOfTitle[position]
        binding.tutorialDescription.text = tutorialModel.listOfDescription[position]
        (container as ViewPager).addView(binding.root)
        return binding.root
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        view: Any
    ) {
        container.removeView(view as View)
    }
}