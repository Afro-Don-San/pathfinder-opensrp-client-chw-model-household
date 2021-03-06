package com.adosa.opensrp.chw.household.util;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.adosa.opensrp.chw.household.R;

import timber.log.Timber;

public class CopyToClipboardDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private String content;

    public CopyToClipboardDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CopyToClipboardDialog(@NonNull Context context, int style) {
        super(context, style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.copy_to_clipboard_widget);
        findViewById(R.id.copyToClipboardMessage).setOnClickListener(this);
        ((TextView) findViewById(R.id.copyToClipboardHeader)).setText(content);
    }

    @Override
    public void onClick(View v) {
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(context.getString(R.string.copy_to_clipboard), this.content);
            clipboard.setPrimaryClip(clip);

            dismiss();
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    public void setContent(String content) {
        this.content = content;
    }
}
