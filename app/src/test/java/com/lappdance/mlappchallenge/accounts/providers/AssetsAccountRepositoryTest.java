package com.lappdance.mlappchallenge.accounts.providers;

import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;
import com.lappdance.mlappchallenge.accounts.models.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class AssetsAccountRepositoryTest {

    private AssetsAccountRepository repo;

    @Before
    public void setUp() {
        repo = new AssetsAccountRepository();
    }

    @Test
    public void loadAccounts() {
        final List<Account> accounts = repo.loadAccounts(RuntimeEnvironment.application);

        assertThat(accounts, hasSize(3));

        final Account first = accounts.get(0);
        assertThat(first.getId(), is(10));
        assertThat(first.getDisplayName(), is("Chequing Account"));
        assertThat(first.getNumber(), is("3157419"));
        assertThat(first.getBalance(), is(closeTo(102406.86, 0.001)));

        assertThat(accounts.get(1).getId(), is(12));
        assertThat(accounts.get(2).getId(), is(19));
    }

    @Test
    public void getAccountActivity_chequing() {
        final List<DailyActivity> activity = repo.getAccountActivity(RuntimeEnvironment.application, 10);

        assertThat(activity, hasSize(4));

        final DailyActivity firstActivity = activity.get(0);
        final Calendar dec22 = Calendar.getInstance();
        //`Calendar` months are 0-based, not 1-based. December is month 11
        dec22.set(2017, 11, 22);
        dec22.set(Calendar.HOUR_OF_DAY, 0);
        dec22.set(Calendar.MINUTE, 0);
        dec22.set(Calendar.SECOND, 0);
        dec22.set(Calendar.MILLISECOND, 0);

        assertThat(firstActivity.getDate(), is(dec22.getTime()));

        final List<Transaction> transactions = firstActivity.getActivity();
        assertThat(transactions, hasSize(1));

        final Transaction pay = transactions.get(0);
        assertThat(pay.getId(), is(61L));
        assertThat(pay.getDate(), is(dec22.getTime()));
        assertThat(pay.getUid(), is(5347227288L));
        assertThat(pay.getAmountDeposited(), is(closeTo(1179.96, 0.001)));
        assertThat(pay.getAmountWithdrawn(), is(0.0));
        assertThat(pay.getBalance(), is(closeTo(102406.86, 0.001)));
        assertThat(pay.getDescription(), is("Manulife Pay"));

        final Transaction aldoShoes = activity.get(1).getActivity().get(0);
        assertThat(aldoShoes.getDescription(), is("ALDO"));
        assertThat(aldoShoes.getAmountWithdrawn(), is(closeTo(37.83, 0.001)));
        assertThat(aldoShoes.getAmountDeposited(), is(0.0));
    }
}
