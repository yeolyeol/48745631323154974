package com.example.admin.scanner;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Window;
import android.view.WindowManager;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MainActivity extends AppCompatActivity implements OnClickListener
{

    private WebView webview;
    EditText editT;
    TextView textV;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.activity_main);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        editT = (EditText)findViewById(R.id.edittext);
        textV = (TextView)findViewById(R.id.textview);


        final EditText edittext=(EditText)findViewById(R.id.edittext);
        Button button=(Button)findViewById(R.id.insert_button);
        final TextView textView=(TextView)findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textView.setText(edittext.getText());
            }
        });




        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

    }
/*
    Button.OnClickListener myButtonClick = new Button.OnClickListener(){
        public void onClick(View v){
            String s;

            String getEdit = editT.getText().toString();

            if(getEdit.getBytes().length <= 0){

                Toast.makeText(this, "값을 입력하세요." Toast.LENGTH_SHORT).show();
            }
            else{
                s = "입력 내용 : " + getEdit;

                textV.setText(s);
*/






    public void onClick(View v){

        if(v.getId()==R.id.scan_button)
        {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            webview = (WebView)findViewById(R.id.webView);
            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setDomStorageEnabled(true);
            webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            webview.loadUrl("https://www.beepscan.com/barcode/" + scanContent);
            //읽어온 바코드값으로 인터넷 페이지 열기



            textV.setText("캔류\n" +
                    "내용물을 비우고 가능한 압착\n" +
                    "겉 또는 속의 플라스틱 뚜껑 등 제거\n" +
                    "담배꽁초 등 이물질을 넣지 말 것");
            //임시
        }

        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "스캔 실패", Toast.LENGTH_SHORT);
            toast.show();
        }
    }












}

