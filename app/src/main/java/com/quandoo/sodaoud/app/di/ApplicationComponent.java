package com.quandoo.sodaoud.app.di;

import com.quandoo.sodaoud.app.job.ReservationScheduler;
import com.quandoo.sodaoud.app.view.CustomersActivity;

/**
 * Created by sofiane on 10/25/16.
 */

//@Singleton
//@Component(modules = {ApplicationModule.class, ApiClientModule.class})
public interface ApplicationComponent {
    void inject(CustomersActivity activity);
    void inject(ReservationScheduler service);
}