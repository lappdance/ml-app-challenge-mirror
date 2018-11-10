package com.lappdance.mlappchallenge.splash;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.Button;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.accounts.AccountActivity;
import com.lappdance.mlappchallenge.authentication.DiskBasedUserRepository;
import com.lappdance.mlappchallenge.splash.SplashViewModel;
import com.lappdance.mlappchallenge.splash.SplashActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    private ActivityController<SplashActivity> mController;
    private SplashViewModel mViewModel;

    private MutableLiveData<Boolean> mNoSession;
    private MutableLiveData<Boolean> mActiveSession;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mActiveSession = new MutableLiveData<>();
        mActiveSession.setValue(true);

        mNoSession = new MutableLiveData<>();
        mNoSession.setValue(false);

        mController = Robolectric.buildActivity(SplashActivity.class);

        mViewModel = ViewModelProviders.of(mController.get()).get(SplashViewModel.class);
    }

    @After
    public void tearDown() {
        DiskBasedUserRepository.setInstance(null);
    }

    @Test
    public void onCreate_loadsView() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        assertThat("no open button!",
                openButton, is(notNullValue()));
    }

    @Test
    public void onCreate_opensActivity() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mActiveSession);
        mController.setup();

        assertOpenedAccountActivity();
    }

    @Test
    public void onCreate_finishesActivity() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mActiveSession);
        mController.setup();

        assertActivityIsFinished();
    }

    @Test
    public void openButton_clicked_opensAccountActivity() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        assertOpenedAccountActivity();
    }

    @Test
    public void openButton_clicked_finishesActivity() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        assertActivityIsFinished();
    }

    @Test
    @Ignore("not sure how to inject the mocked ViewModel")
    public void openButton_clicked_createsSession() {
        DiskBasedUserRepository.getInstance(RuntimeEnvironment.application).setLoggedIn(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        verify(mViewModel).login();
    }

    private void assertOpenedAccountActivity() {
        ShadowActivity shadowActivity = shadowOf(mController.get());
        final Intent intent = shadowActivity.getNextStartedActivity();
        assertThat("no Activity started on button press",
                intent, is(notNullValue()));

        final ComponentName name = intent.getComponent();
        assertThat("no Activity specified in intent",
                name, is(notNullValue()));
        assertThat("wrong Activity started",
                name.getClassName(), is(AccountActivity.class.getName()));

    }

    private void assertActivityIsFinished() {
        assertThat(mController.get().isFinishing(), is(true));
    }
}
