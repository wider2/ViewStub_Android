package stub.viewstub;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


public class HomeFragmentPresenter {

    private static final String TAG = "SEARCH_API";
    private IHomeFragment mainView;
    private Context mContext;

    public HomeFragmentPresenter(IHomeFragment mainView, Context ctx) {
        this.mainView = mainView;
        this.mContext = ctx;
        //subject = PublishSubject.create();

        //DaggerCouponComponent.builder().couponModule(new CouponModule()).build();
        //this.couponRepository = DaggerCouponComponent.create().getCouponRep();

        //this.couponRepositoryProvider = SingleCheck.provider(CouponRepository_Factory.create());
    }

    protected String getTag() {
        return HomeFragmentPresenter.class.getName();
    }


    public void getPresenterMethod(final String query) {
        int r = new Random().nextInt(999);
        try {
            //here we download something

            mainView.refreshResult("" + r);

        } catch (Exception ex) {
            Log.wtf(TAG, ex.getMessage());
            mainView.showErrorServerResponse(ex);
        }

    }

}