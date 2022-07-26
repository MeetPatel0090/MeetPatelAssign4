// Meet Patel N01460090 Section:- RNB

package meet.patel.n01460090;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MeetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        // Handle item selection
        switch (menuitem.getItemId())
        {
            case R.id.MeetMenuItem1:
                break;

            case R.id.MeetMenuItem2:
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                break;

            case R.id.MeetMenuItem3:
                gotoUrl();
                break;

            default:
                return super.onOptionsItemSelected(menuitem);

        }
        return true;

    }

    private void gotoUrl()
    {
        Uri uri = Uri.parse("https://www.android.com/intl/en_ca/");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

}