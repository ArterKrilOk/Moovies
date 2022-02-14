package space.pixelsg.moovies.ui.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

import space.pixelsg.moovies.R;

public class SearchView extends AppCompatEditText {
    public SearchView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSingleLine(true);
        setMaxLines(1);
        setMinLines(1);

        setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_round_search_24,
                0
        );

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        s.toString().isEmpty()? R.drawable.ic_round_search_24 : R.drawable.ic_round_clear_24,
                        0
                );
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        setOnTouchListener((v, event) -> {
            if(event.getX() >= v.getWidth() - (v.getPaddingEnd() * 2)) {
                if(event.getAction() == MotionEvent.ACTION_UP && getText().toString().isEmpty()) {
                    setText("");
                    onEditorAction(EditorInfo.IME_ACTION_SEARCH);
                    clearFocus();
                    performClick();
                    return true;
                }
            }
            return false;
        });
    }
}
