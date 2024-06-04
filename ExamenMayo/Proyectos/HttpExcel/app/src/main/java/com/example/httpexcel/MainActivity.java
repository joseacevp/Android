package com.example.httpexcel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httpexcel.databinding.ActivityMainBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> nombres, referencias, imagemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String url = "https://github.com/joseacevp/Android/blob/7e3c1ad68cca2701b8ed29a77d9b039a86485864/AppExcel.xlsx";

        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this, "Fallo al descargar ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(MainActivity.this, "Descargado fichero Excel", Toast.LENGTH_SHORT).show();
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file != null) {
                    try {
                        workbook = Workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int i = 0; i < sheet.getRows(); i++) {
                            Cell[] row = sheet.getRow(i);
                            nombres.add(row[0].getContents());
//                            referencias.add(row[1].getContents());
//                            imagemUrl.add(row[2].getContents());
                        }
                        mostrarDatos();

                    } catch (IOException ex) {
//                        throw new RuntimeException(e);
                        Toast.makeText(MainActivity.this, "IOException", Toast.LENGTH_SHORT).show();
                    } catch (BiffException e) {
                        Toast.makeText(MainActivity.this, "BiffException", Toast.LENGTH_SHORT).show();
                      //  throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void mostrarDatos() {
        String dato = "";
        for (String datos:nombres
             ) {
            dato += datos;
        }
        System.out.println(dato);
    }
}