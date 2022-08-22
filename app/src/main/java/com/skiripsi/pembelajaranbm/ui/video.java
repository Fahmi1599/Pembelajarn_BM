package com.skiripsi.pembelajaranbm.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.skiripsi.pembelajaranbm.R;

public class video extends YouTubeBaseActivity {



    public static final String API_KEY = "AIzaSyD8qHQDBLWA9I96BBVYeH18lGLvZ5X4WTw";
    private String link;

    WebView wv_term;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        wv_term = findViewById(R.id.youtube_player_view);


        link = getIntent().getStringExtra("videoLink");

        //inisialisasi YouTubePlayerView
        wv_term.getSettings().setLoadsImagesAutomatically(true);

        wv_term.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat

        // melakukan zoom.

        wv_term.getSettings().setSupportZoom(true);

        wv_term.getSettings().setBuiltInZoomControls(true);

        wv_term.getVerticalScrollbarPosition();

        wv_term.getSettings().setDisplayZoomControls(true);

        wv_term.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        wv_term.computeScroll();

        wv_term.getSettings().getJavaScriptEnabled();

        wv_term.getSettings().setJavaScriptEnabled(true);

        wv_term.getSettings().setAllowFileAccess(true);

        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya

        wv_term.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        wv_term.setWebViewClient(new WebViewClient());

        wv_term.loadUrl(link);

    }
}
