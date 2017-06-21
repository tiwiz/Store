package com.nytimes.android.sample.activity;

import android.widget.TextView;

import com.nytimes.android.external.store.base.Bar;
import com.nytimes.android.external.store.base.annotation.Resizable;
import com.nytimes.android.external.store.base.annotation.Resize;

import org.w3c.dom.Text;

@Resize
public class Foo {
    @Resizable
    TextView bar;

    @Resizable
    TextView lolz;
}
