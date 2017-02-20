package com.example.th.onepushmail.interfaces;

import java.util.EventListener;

/**
 * Created by T.H on 2016/12/29.
 *
 * DialogFragment
 * - private DialogListener listener = null;
 * - listenr.positiveClick() / listenr.negativeClick()
 *
 * Activity
 * - implement DialogListener
 */

public interface DialogListener extends EventListener{
    /**
     * Positiveボタン
     */
    public void positiveClick(int which);

    /**
     * Negativeボタン
     */
    public void negativeClick(int which);
}
