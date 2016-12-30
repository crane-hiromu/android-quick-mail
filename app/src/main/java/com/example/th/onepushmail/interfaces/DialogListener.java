package com.example.th.onepushmail.interfaces;

import java.util.EventListener;

/**
 * Created by T.H on 2016/12/29.
 */

public interface DialogListener extends EventListener{
    /**
     * Positiveボタン
     */
    public void positiveClick();

    /**
     * Negativeボタン
     */
    public void negativeClick();
}
