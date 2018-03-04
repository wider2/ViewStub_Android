package stub.viewstub;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_viewStub)
    ViewStub simpleViewStub;

    @BindView(R.id.bt_showButton)
    Button showButton;
    @BindView(R.id.bt_hideButton)
    Button hideButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        simpleViewStub.inflate();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.home_fragment_container, new HomeFragment(), HomeFragment.class.getName())
                    .commit();
        }

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleViewStub.setVisibility(View.VISIBLE);
            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleViewStub.setVisibility(View.GONE);
            }
        });
    }

}
