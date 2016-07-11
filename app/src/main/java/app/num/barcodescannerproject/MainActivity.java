package app.num.barcodescannerproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private String textResult;

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";



    /*
    de onde eu tirei a base do codigo
    foi por esse site do link logo abaixo

    https://www.numetriclabz.com/android-qr-code-scanner-using-zxingscanner-library-tutorial/#Get_the_best_of_Android_plus_exclusive_deals_and_freebies_in_your_inbox
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void QrScanner(View view){


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        /*
        ISTO PRECISA DE ALGUMA REVISAO
         */
        mScannerView.stopCamera();              //stop camera
        textResult = rawResult.getText();       //put result into textResult
        sendMessage();                          //sends result to anoter activity
    }

    public void sendMessage() {
        Intent intent = new Intent(this, Activity_2.class);
        intent.putExtra(EXTRA_MESSAGE,textResult);
        startActivity(intent);
    }
}
