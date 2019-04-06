package com.wulinpeng.daiylreader.bookdetail.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 11:05 PM
 * @description:
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
class BookCoverTransition: Transition() {

    companion object {
        const val PROPERTY_SCALE_X = "BookCoverTransition_property_scale_x"
        const val PROPERTY_SCALE_Y = "BookCoverTransition_property_scale_y"
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureScaleValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureScaleValues(transitionValues)
    }

    private fun captureScaleValues(transitionValues: TransitionValues) {
        transitionValues.values[PROPERTY_SCALE_X] = transitionValues.view.scaleX
        transitionValues.values[PROPERTY_SCALE_Y] = transitionValues.view.scaleY
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        var view = endValues?.view ?: return null
        var animatorX = ObjectAnimator.ofFloat(view, "scaleX", startValues?.values?.get(PROPERTY_SCALE_X) as Float ?: view.scaleX, 3f, endValues?.values?.get(PROPERTY_SCALE_X) as Float ?: view.scaleX)
        var animatorY = ObjectAnimator.ofFloat(view, "scaleY", startValues?.values?.get(PROPERTY_SCALE_Y) as Float ?: view.scaleY, 3f, endValues?.values?.get(PROPERTY_SCALE_Y) as Float ?: view.scaleY)
        return AnimatorSet().apply {
            playTogether(animatorX, animatorY)
        }
    }
}