// Meet Patel N01460090 Section:- RNB

package meet.patel.n01460090;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MeetActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);

        Toolbar toolbar = findViewById(R.id.MeetToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.MeetNavigationDrawer);
        navigationView = findViewById(R.id.MeetNavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close){
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                Snackbar.make(findViewById(android.R.id.content),R.string.nav_open_msg,Snackbar.LENGTH_SHORT).show();
            }
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                Toast.makeText(getApplicationContext(),R.string.nav_close_msg, Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.addDrawerListener(toggle);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                    new MeetHome()).commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meet_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem)
    {

        // Handle item selection
        switch (menuitem.getItemId()) {
            case R.id.MeetMenuHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new MeetHome()).commit();
                return true;

            case R.id.MeetMenuSettings:
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                return true;

            case R.id.MeetMenuHelp:
                gotoUrl();
                return true;


            default:
                return super.onOptionsItemSelected(menuitem);
        }
    }

    private void gotoUrl()
    {
        Uri uri = Uri.parse("https://www.android.com/intl/en_ca/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MeetNavHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new MeetHome()).commit();
                break;
            case R.id.MeetNavDownload:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new PatelDownload()).commit();
                break;
            case R.id.MeetNavWeather:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new N01460090Weather()).commit();
                break;
            case R.id.MeetNavFileContent:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new MeetFileContent()).commit();
                break;
            case R.id.MeetNavSettings:
                getSupportFragmentManager().beginTransaction().replace(R.id.MeetFrameLayout,
                        new SettingsScreen()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MeetActivity.this);

        alertDialog.setPositiveButton(R.string.alert_yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton(R.string.alert_no, null);

        alertDialog.setMessage(R.string.alert_meessage);
        alertDialog.setTitle(R.string.alert_title);
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

        doExit();
    }
}