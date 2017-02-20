package com.example.th.onepushmail.views.animation;

import android.app.Activity;
import android.view.View;

import com.example.th.onepushmail.utils.CommonUtil;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * アニメーション定義(単発)
 * <p>
 * Created by T.H on 2016/12/25.
 * <p>
 * <p>
 * Listener
 * AnimationListener.Start startListener = new AnimationListener.Start(){
 *
 * @Override public void onStart() {}
 * };
 * <p>
 * AnimationListener.Stop stopListener = new AnimationListener.Stop(){
 * @Override public void onStop() {}
 * };
 */


public class ViewAnimations {
    ViewAnimations self = this;

    /**
     * alphaアニメーション（visible）
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
    }

    //２つのパーツをvisible
    public static void visible(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(0, 1)
                .duration(time)
                .start();
    }

    //３つのパーツをvisible
    public static void visible(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(0, 1)
                .duration(time)
                .start();
    }

    //１つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
    }

    //２つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, View second, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
    }

    //３つのパーツをvisible //始まりと終わりのalpha値を設定できる
    public static void visible(View first, View second, View third, int alphaStart, int alphaEnd, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(alphaStart, alphaEnd)
                .duration(time)
                .start();
    }


    /**
     * alphaアニメーション（invisible）
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
    }

    //２つのパーツを同時にinvisible
    public static void invisible(View first, View second, int time) {
        ViewAnimator
                .animate(first, second)
                .alpha(1, 0)
                .duration(time)
                .start();
    }

    //３つのパーツを同時にinvisible
    public static void invisible(View first, View second, View third, int time) {
        ViewAnimator
                .animate(first, second, third)
                .alpha(1, 0)
                .duration(time)
                .start();
    }

    /**
     * 縦横の大きさ変更アニメーション
     * <p>
     * 例 ViewAnimations.spread(view,横,縦,時間);
     */

    public static void variable(View v, Integer width, Integer height, int time) {
        ViewAnimator
                .animate(v)
                .width(width)
                .height(height)
                .duration(time)
                .start();
    }

    //full screen
    public static void fullscreen(View v, Activity activity, int time) {
        ViewAnimator
                .animate(v)
                .width(CommonUtil.getScreenSize(activity).x)
                .height(CommonUtil.getScreenSize(activity).y)
                .duration(time)
                .start();
    }

    /**
     *  width,height可変 × alphaアニメーション
     * <p>
     * 実装例
     * ViewAnimations.visibleSize(View,[0,0],[100,100],time);
     * （可変前、可変後の数値を配列として渡す）
     */

    /**
     * VISIBLE
     * <p>
     * width,height同じ
     */
    public static void visibleSize(View v, Integer before, Integer after, int time) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .start();
    }

    //startlistener
    public static void visibleSize(View v, Integer before, Integer after, int time, AnimationListener.Start listener) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .onStart(listener)
                .start();
    }

    //stoplistener
    public static void visibleSize(View v, Integer before, Integer after, int time, AnimationListener.Stop listener) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .onStop(listener)
                .start();
    }

    /**
     * width,height別々
     */
    public static void visibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .start();
    }

    //startlistener
    public static void visibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time, AnimationListener.Start listener) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .onStart(listener)
                .start();
    }

    //stoplistener
    public static void visibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time, AnimationListener.Stop listener) {
        ViewAnimator
                .animate(v)
                .alpha(0, 1)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .onStop(listener)
                .start();
    }

    /**
     * INVISIBLE
     * <p>
     * width,height同じ
     */
    public static void invisibleSize(View v, Integer before, Integer after, int time) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .start();
    }

    //startlistener
    public static void invisibleSize(View v, Integer before, Integer after, int time, AnimationListener.Start listener) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .onStart(listener)
                .start();
    }

    //stoplistener
    public static void invisibleSize(View v, Integer before, Integer after, int time, AnimationListener.Stop listener) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(before, after)
                .dp().height(before, after)
                .duration(time)
                .onStop(listener)
                .start();
    }

    /**
     * width,height別々
     */
    public static void invisibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .start();
    }

    //startlistener
    public static void invisibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time, AnimationListener.Start listener) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .onStart(listener)
                .start();
    }

    //stoplistener
    public static void invisibleSize(View v, Integer wBefore, Integer hBefore, Integer wAfter, Integer hAfter, int time, AnimationListener.Stop listener) {
        ViewAnimator
                .animate(v)
                .alpha(1, 0)
                .dp().width(wBefore, wAfter)
                .dp().height(hBefore, hAfter)
                .duration(time)
                .onStop(listener)
                .start();
    }
}
