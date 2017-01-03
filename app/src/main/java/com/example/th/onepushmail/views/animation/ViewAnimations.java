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
     * ViewAnimations.visible(引数、、、);
     */
    //１つのパーツをvisible
    public static void visible(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    //２つのパーツをvisible
    public static void visible(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    //３つのパーツをvisible
    public static void visible(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(0, 1)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
    }

    //１つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
    }

    //２つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, View second, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    //３つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, View second, View third, int alphaStart, int alphaEnd, int time) {
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
     * alphaアニメーション（INVISIBLE）
     * <p>
     * 例 ViewAnimations.invisible(view,時間);
     */

    //１つのパーツをinvisible
    public static void invisible(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.INVISIBLE);
    }

    //２つのパーツを同時にinvisible
    public static void invisible(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.INVISIBLE);
    }

    //３つのパーツを同時にinvisible
    public static void invisible(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.INVISIBLE);
        third.setVisibility(View.INVISIBLE);
    }

    /**
     * alphaアニメーション（GONE）
     * <p>
     * 例 ViewAnimations.gone(view,時間);
     */

    //１つのパーツをgone
    public static void gone(View first, int time) {
        ViewAnimator
                .animate(first)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
    }

    //２つのパーツを同時にgone
    public static void gone(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(1, 0)
                .duration(time)
                .start();
        first.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
    }

    //３つのパーツを同時にgone
    public static void gone(View first, View second, View third, int time) {
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
