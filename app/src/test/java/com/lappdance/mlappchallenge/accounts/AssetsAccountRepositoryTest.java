package com.lappdance.mlappchallenge.accounts;

import com.lappdance.mlappchallenge.accounts.models.Account;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

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

        assertThat(accounts, IsCollectionWithSize.hasSize(3));

        final Account first = accounts.get(0);
        assertThat(first.getId(), is(10));
        assertThat(first.getDisplayName(), is("Chequing Account"));
        assertThat(first.getNumber(), is("3157419"));
        assertThat(first.getBalance(), is(closeTo(102406.86, 0.01)));

        assertThat(accounts.get(1).getId(), is(12));
        assertThat(accounts.get(2).getId(), is(19));
    }
}
