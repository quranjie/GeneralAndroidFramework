package com.jingdong.sdk.generalandroidframework.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.jingdong.sdk.generalandroidframework.R;
import com.jingdong.sdk.generalandroidframework.ui.base.BaseActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsImageView;

/**
 * 本示例只简单展示了Iconic的使用，具体请参考README.md字体型图标篇
 */
public class IconicActivity extends BaseActivity {

    private IconicsImageView test_iiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconic);

        Drawable d = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_android)
                .color(Color.RED)
                .sizeDp(24);

        test_iiv = (IconicsImageView) findViewById(R.id.test_iiv);
        test_iiv.setImageDrawable(d);

    }
}
