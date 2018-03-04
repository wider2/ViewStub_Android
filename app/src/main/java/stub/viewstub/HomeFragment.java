package stub.viewstub;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.IgnoreWhen;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements IHomeFragment {

    private static final String TAG = "TAG_API";
    private Bundle mSavedInstanceState;
    private boolean mHasInflated = false;
    HomeFragmentPresenter mPresenter;

    @BindView(R.id.fragment_viewStub)
    ViewStub fragmentViewStub;

    @BindView(R.id.tv_result)
    TextView tvResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //RxJavaSearchApplication app = RxJavaSearchApplication.getApp();
        //DaggerPersistentComponent.builder().appModule(new AppModule(app)).build().inject(this);
    }

    @AfterViews
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        mPresenter = new HomeFragmentPresenter(this, getContext());
        mSavedInstanceState = savedInstanceState;

        //The layout ID associated with this ViewStub
        fragmentViewStub.setLayoutResource(R.layout.custom_stub2);

        try {
            //check whether the current fragment is already visible to user
            if (getUserVisibleHint() && !mHasInflated) {
                View inflatedView = fragmentViewStub.inflate();
                fragmentViewStub.setVisibility(View.VISIBLE);

                onCreateViewAfterViewStubInflated(inflatedView, mSavedInstanceState);

                afterViewStubInflated(v);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return v;
    }

    //just callback
    private void onCreateViewAfterViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mPresenter.getPresenterMethod("query to search");
    }


    @CallSuper
    protected void afterViewStubInflated(View originalViewContainerWithViewStub) {
        mHasInflated = true;
        if (originalViewContainerWithViewStub != null) {
            View pb = originalViewContainerWithViewStub.findViewById(R.id.tv_result);
            pb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && fragmentViewStub != null && !mHasInflated) {
            View inflatedView = fragmentViewStub.inflate();
            onCreateViewAfterViewStubInflated(inflatedView, mSavedInstanceState);
            afterViewStubInflated(getView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHasInflated = false;
    }

    @UiThread
    @Override
    public void refreshResult(String result) {
        tvResult.setText("Output: " + result);
    }

    @UiThread
    @Override
    public void showErrorServerResponse(Throwable response) {
        tvResult.setText(response.getMessage());
    }

}