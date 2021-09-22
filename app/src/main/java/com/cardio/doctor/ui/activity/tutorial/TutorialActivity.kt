package com.cardio.doctor.ui.activity.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.cardio.doctor.R
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.databinding.ActivityTutorialBinding
import com.cardio.doctor.model.TutorialModel
import com.cardio.doctor.ui.activity.AuthenticateUserActivity
import com.cardio.doctor.ui.activity.tutorial.adapter.SliderAdapter
import com.cardio.doctor.utils.setDoubleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : BaseActivity() {
    private val binding by viewBinding(ActivityTutorialBinding::inflate)
    private lateinit var tutorialModel: TutorialModel
    private var currentPos = 0
    private val tutorialImages = arrayListOf(R.drawable.ic_tutorial_one,
        R.drawable.ic_tutorial_two,
        R.drawable.ic_tutorial_three
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
        setListeners()
    }

    private fun setAdapter() {
        tutorialModel = TutorialModel(tutorialImages,
            resources.getStringArray(R.array.TutorialTitle).toList(),
            resources.getStringArray(R.array.TutorialDescription).toList()
        )
        binding.viewPagerTutorial.adapter = SliderAdapter(this, tutorialModel)
        binding.viewPagerTutorial.addOnPageChangeListener(changeListener)
        binding.dotContainer.setViewPager(binding.viewPagerTutorial)
    }

    private fun setListeners() {
        binding.tutorialSkip.setDoubleClickListener {
            binding.tutorialSkip.isEnabled = false
            openUserAuthActivity()
        }

        binding.btnGetStarted.setDoubleClickListener {
            openUserAuthActivity()
        }

        binding.imgNext.setDoubleClickListener {
            binding.viewPagerTutorial.currentItem = currentPos + 1
        }
    }

    private fun openUserAuthActivity(){
        startActivity(Intent(this, AuthenticateUserActivity::class.java))
        finish()
    }

    private var changeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageSelected(position: Int) {
            currentPos = position
            if (position == tutorialModel.listOfImages.size-1) btnVisibility(View.VISIBLE, View.GONE,View.INVISIBLE)
            else  btnVisibility(View.GONE, View.VISIBLE,View.VISIBLE)
        }
    }

    private fun btnVisibility(visibilityGetStarted: Int, visibilityNext: Int,skipVisibilty : Int) {
        binding.btnGetStarted.visibility = visibilityGetStarted
        binding.imgNext.visibility = visibilityNext
        binding.tutorialSkip.visibility = skipVisibilty

    }
}