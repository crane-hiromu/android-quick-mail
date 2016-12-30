package com.example.th.onepushmail.views.animation;

import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;

/**
 * アニメーション定義
 * <p>
 * Created by T.H on 2016/12/25.
 */

public class ViewAnimations {
    ViewAnimations self = this;

    /**
     * alphaアニメーション（出現）
     *
     * ViewAnimations.appear(引数、、、);
     */
    public static void appear(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    public static void appear(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    public static void appear(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
    }

    //始まりと終わりのalpha値を設定できる
    public static void appear(View first, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    public static void appear(View first, View second, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    public static void appear(View first, View second, View third, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
    }

    /**
     * alphaアニメーション（消滅）
     *
     * ViewAnimations.disappear(引数、、、);
     */
    public static void disappear(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
    }

    public static void disappear(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
    }

    public static void disappear(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
        third.setVisibility(View.GONE);
    }
}
