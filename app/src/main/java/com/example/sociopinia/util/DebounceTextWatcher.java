package com.example.sociopinia.util;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

public class DebounceTextWatcher implements TextWatcher {

    private long delay;
    private long lastTextEdit = 0;
    private Handler handler = new Handler();
    private Runnable debounceAction;
    private Runnable inputFinishChecker = () -> {
        if (System.currentTimeMillis() > (lastTextEdit + delay - 500)) {
            debounceAction.run();
        }
    };

    public DebounceTextWatcher(Runnable action, long delay) {
        this.debounceAction = action;
        this.delay = delay;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        handler.removeCallbacks(inputFinishChecker);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() > 0) {
            lastTextEdit = System.currentTimeMillis();
            handler.postDelayed(inputFinishChecker, delay);
        }
    }
}
