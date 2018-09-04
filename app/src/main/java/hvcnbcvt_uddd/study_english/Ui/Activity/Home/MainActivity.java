package hvcnbcvt_uddd.study_english.Ui.Activity.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.sql.SQLException;

import butterknife.BindView;
import hvcnbcvt_uddd.study_english.Data.Local.DBHelper;
import hvcnbcvt_uddd.study_english.R;
import hvcnbcvt_uddd.study_english.Ui.Activity.Vocabulary.VocabularyActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewFlipper mViewFlipper;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Controls();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


        DBHelper db = new DBHelper(this);

        //Thêm database
//        try {
//            db.deleteDataBase();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }

        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Events();
    }

    private void Events() {
        slideAdvertise();
    }

    private void slideAdvertise() {
        int image[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
        mViewFlipper = (ViewFlipper) findViewById(R.id.flipper1);
        for (int images : image) {
            flipperImage(images);
        }
    }

    public void flipperImage(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        mViewFlipper.addView(imageView);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    private void Controls() {
         navigationView = (NavigationView) findViewById(R.id.nav_view);
         mViewFlipper = (ViewFlipper) findViewById(R.id.flipper1);
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
        if (id == R.id.action_en) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vocabulary) {
            Intent intent = new Intent(this, VocabularyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_grammar) {

        } else if (id == R.id.nav_basic_test) {

        } else if (id == R.id.nav_exam) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
