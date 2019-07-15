package com.ronmaster.testadorpratico;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String [] imagens = new String[] {
            "https://api.pcloud.com/getpubthumb?code=XZPIu37ZnxHGdRwroPytdsIF159ISfSSozS7&linkpassword=undefined&size=800x448&crop=0&type=auto",
            "https://api.pcloud.com/getpubthumb?code=XZvIu37ZTahAVsJFCRXomx4QfNWBBYC5YY0V&linkpassword=undefined&size=1280x720&crop=0&type=auto",
            "https://api.pcloud.com/getpubthumb?code=XZiIu37ZGRiMcRxPsUbud1cSVLJaUJuqAY0V&linkpassword=undefined&size=1365x680&crop=0&type=auto",
            "https://api.pcloud.com/getpubthumb?code=XZcIu37Zc7TPakyUDeJXbSQD3UDoWjDjDIGX&linkpassword=undefined&size=1000x848&crop=0&type=auto",
            "https://api.pcloud.com/getpubthumb?code=XZfIu37ZMLWo0jpdD74CTbkjdIbGpu4hSePy&linkpassword=undefined&size=1366x768&crop=0&type=auto"};

    ViewPager viewPager;
    LinearLayout sliderDotsPainel;
    private int dotsCount;
    private ImageView[] dots;

    //vars categoria
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<Integer> mImagensURL = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        sliderDotsPainel = (LinearLayout) findViewById(R.id.SliderDots);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imagens);
        viewPager.setAdapter(adapter);

        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for(int i = 0; i < dotsCount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot_none));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);
            sliderDotsPainel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot_none));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1000,4000);

        getImagesInit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }
                    else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }
                    else if(viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);
                    }
                    else
                    {
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }


    private void getImagesInit(){

        mImagensURL.add(R.drawable.comida);
        mNomes.add("Comida caseira");

        mImagensURL.add(R.drawable.padaria);
        mNomes.add("Padarias e lanchonetes");

        mImagensURL.add(R.drawable.doceirias);
        mNomes.add("Docerias");

        mImagensURL.add(R.drawable.salgados);
        mNomes.add("Salgados e tele-entrega");

        mImagensURL.add(R.drawable.restaurantes);
        mNomes.add("Restaurantes");

        mImagensURL.add(R.drawable.acougues);
        mNomes.add("Açougues");

        mImagensURL.add(R.drawable.lojas_de_roupa);
        mNomes.add("Moda e vestuário");

        mImagensURL.add(R.drawable.supermercados_da_regiao);
        mNomes.add("Mercados");

        mImagensURL.add(R.drawable.medicos);
        mNomes.add("Saúde");

        mImagensURL.add(R.drawable.farmacia);
        mNomes.add("Farmacias");

        mImagensURL.add(R.drawable.servicos);
        mNomes.add("Serviços diversos");

        mImagensURL.add(R.drawable.festas_e_eventos);
        mNomes.add("Festas e eventos");

        initRecyclerViewCategoria();
    }

    private void initRecyclerViewCategoria(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycler_category);
        recyclerView.setLayoutManager(linearLayoutManager);
        CategoriaRecyclerViewAdapter adapter = new CategoriaRecyclerViewAdapter(this,mNomes,mImagensURL);
        recyclerView.setAdapter(adapter);
    }

}
