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
     * <p>
     * ViewAnimations.appear(引数、、、);
     */
    //１つのパーツをappear
    public static void appear(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    //２つのパーツをappear
    public static void appear(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    //３つのパーツをappear
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

    //１つのパーツをappear //始まりと終わりのalpha値を設定できる
    public static void appear(View first, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    //２つのパーツをappear //始まりと終わりのalpha値を設定できる
    public static void appear(View first, View second, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    //３つのパーツをappear //始まりと終わりのalpha値を設定できる
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
     * <p>
     * 例 ViewAnimations.disappear(view,時間);
     */

    //１つのパーツをdisappear
    public static void disappear(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
    }

    //２つのパーツを同時にdisappear
    public static void disappear(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
    }

    //３つのパーツを同時にdisappear
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
