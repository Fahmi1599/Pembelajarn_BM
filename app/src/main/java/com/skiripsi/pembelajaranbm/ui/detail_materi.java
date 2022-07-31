package com.skiripsi.pembelajaranbm.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skiripsi.pembelajaranbm.R;

import java.net.URLEncoder;

public class detail_materi extends AppCompatActivity {

    WebView pdfView;
    private String pdfLink;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);

        pdfView = findViewById(R.id.wv_pdf);
        pdfView.getSettings().setJavaScriptEnabled(true);
        pdfView.getSettings().setSupportZoom(true);
        pdfLink = getIntent().getStringExtra("linkPDF");
        ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Opening Document....");
        pd.show();

        pdfView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });
        String url="";
        try {
            url= URLEncoder.encode(pdfLink,"UTF-8");
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        pdfView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url="+ url);


    }
}
