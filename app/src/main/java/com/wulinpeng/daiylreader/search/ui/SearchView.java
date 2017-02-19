package com.wulinpeng.daiylreader.search.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午9:25
 * @description:
 */
public class SearchView extends LinearLayout{

    public interface OnSearchListener {
        public void onSearch(String text);
    }

    private OnSearchListener listener;

    @BindView(R.id.et_search)
    public EditText editText;

    @BindView(R.id.btn_clear)
    public ImageView clearButton;

    private Context context;

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.search_layout, this);
        ButterKnife.bind(this);
        initViews();
    }

    public void setListener(OnSearchListener listener) {
        this.listener = listener;
    }

    private void initViews() {
        clearButton.setOnClickListener(v -> {
            editText.setText("");
            clearButton.setVisibility(GONE);
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(s.toString())) {
                    clearButton.setVisibility(VISIBLE);
                }else {
                    clearButton.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (listener != null) {
                        listener.onSearch(textView.getText().toString());
                    }
                    hideSoftInput(context);
                }
                return true;
            }
        });
    }

    public String getInputText() {
        return editText.getText().toString();
    }

    public void hideSoftInput(Context context) {
        View view = ((Activity) context).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
