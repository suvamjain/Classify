package suvamjain.example.com.dbmsproject.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;

import suvamjain.example.com.dbmsproject.R;

/**
 * Created by SUVAM JAIN on 20-04-2017.
 */

public class PdfActivity extends AppCompatActivity {
   // Note: If your file is located in assets/folder/file.ext, then pathInAssets would be "folder/file.ext"

    TextView no_books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        getSupportActionBar().hide();

        String file_name = getIntent().getStringExtra("Code");

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        no_books = (TextView) findViewById(R.id.noBooks);

        AssetManager mg = getResources().getAssets();
        InputStream is = null;
        try {
            is = mg.open(file_name);
//            Toast.makeText(getApplicationContext(),file_name,Toast.LENGTH_SHORT).show();
            pdfView.fromAsset(file_name).load();
        } catch (IOException ex) {
            //file does not exist
            no_books.setVisibility(View.VISIBLE);
            pdfView.setVisibility(View.GONE);
//            Toast.makeText(getApplicationContext(),file_name,Toast.LENGTH_SHORT).show();

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, OptionsActivity.class));
    }

}
