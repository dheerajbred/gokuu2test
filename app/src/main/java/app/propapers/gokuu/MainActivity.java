package app.propapers.gokuu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



import java.util.LinkedList;

import app.propapers.gokuu.download.M3u8DownloadFactory;
import app.propapers.gokuu.listener.DownloadListener;
import app.propapers.gokuu.utils.Constant;


public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "M3U8er";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String M3U8URL = "https://videozmcdn.stz8.com:8091/20191127/PK7a0LKQ/index.m3u8";
        M3u8DownloadFactory.M3u8Download m3u8Download = M3u8DownloadFactory.getInstance(M3U8URL);
        m3u8Download.setDir(getExternalCacheDir().toString());
        m3u8Download.setFileName("test");
        m3u8Download.setThreadCount(100);
        m3u8Download.setRetryCount(100);
        m3u8Download.setTimeoutMillisecond(10000L);
        m3u8Download.setLogLevel(Constant.DEBUG);
        m3u8Download.setInterval(500L);
        m3u8Download.addListener(new DownloadListener() {
            @Override
            public void start() {
                System.out.println("/////////////////////////////////start！");
            }

            @Override
            public void process(String downloadUrl, int finished, int sum, float percent) {
                System.out.println("///////////////////////////////process：" + downloadUrl + "\tdownloadUrl" + finished + "个\tfinished" + sum + "个\tsum" + percent + "%");
            }

            @Override
            public void speed(String speedPerSecond) {
                System.out.println("///////////////////////speed：" + speedPerSecond);
            }

            @Override
            public void end() {
                System.out.println("////////////////////////end");
            }
        });
        m3u8Download.start();


    }


}
