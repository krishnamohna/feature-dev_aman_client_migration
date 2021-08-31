package com.cardio.doctor.ui.activity.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.cardio.doctor.R
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.databinding.ActivityTutorialBinding
import com.cardio.doctor.model.TutorialModel
import com.cardio.doctor.ui.activity.AuthenticateUserActivity
import com.cardio.doctor.ui.activity.tutorial.adapter.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : BaseActivity() {
    private val binding by viewBinding(ActivityTutorialBinding::inflate)
    private lateinit var tutorialModel: TutorialModel
    private lateinit var dots: Array<TextView?>
    private var currentPos = 0
    private val tutorialImages = arrayListOf(R.drawable.ic_tutorial_two,
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
        addDots(0)
    }

    private fun setListeners() {
        binding.btnGetStarted.setOnClickListener {
            openUserAuthActivity()
        }

        binding.imgNext.setOnClickListener {
            binding.viewPagerTutorial.currentItem = currentPos + 1
        }
    }

    private fun openUserAuthActivity(){
        startActivity(Intent(this, AuthenticateUserActivity::class.java))
        finish()
    }

    private fun addDots(position: Int) {
        dots = arrayOfNulls(tutorialModel.listOfImages.size)
        binding.dotContainer.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text =HtmlCompat.fromHtml("•", HtmlCompat.FROM_HTML_MODE_LEGACY)
            dots[i]!!.textSize = resources.getDimension(R.dimen._11ssp)
            dots[i]!!.setTextColor(ContextCompat.getColor(this, R.color.btn_gradient_end))
            binding.dotContainer.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[position]!!.setTextColor(ContextCompat.getColor(this, R.color.green_tutorial_indicator))
        }
    }

    private var changeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPos = position
            if (position == tutorialModel.listOfImages.size-1) btnVisibility(View.VISIBLE, View.GONE)
            else  btnVisibility(View.GONE, View.VISIBLE)
        }
    }

    private fun btnVisibility(visibilityGetStarted: Int, visibilityNext: Int) {
        binding.btnGetStarted.visibility = visibilityGetStarted
        binding.imgNext.visibility = visibilityNext
    }
}