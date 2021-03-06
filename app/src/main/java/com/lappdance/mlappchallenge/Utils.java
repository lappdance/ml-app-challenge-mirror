package com.lappdance.mlappchallenge;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Utils {

    /**
     * Replacement for Objects.requireNonNull.
     * That method is not present in API < 19.
     * @param any Optional. Any Object
     * @param message The message to show if {@code any} is null.
     * @throws IllegalStateException if {@code any} is null.
     */
    @Contract("null, _ -> fail")
    public static void requireNonNull(@Nullable Object any, @NonNull String message) {
        if (any == null) {
            throw new IllegalStateException(message);
        }
    }

    @NonNull
    public static String formattedCurrency(double amount) {
        final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatter.format(amount);
    }
}
