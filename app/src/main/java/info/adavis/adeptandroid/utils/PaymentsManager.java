package info.adavis.adeptandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import info.adavis.adeptandroid.models.Book;
import info.adavis.adeptandroid.services.BillingService;

public class PaymentsManager {

    private Context context;

    public PaymentsManager(Context context) {
        this.context = context;
    }

    public void purchaseBook(Book book) {
        if (isBookValid(book) && !isAlreadyPurchased(book)) {
            BillingService.startActionPurchase(context, book.getBookUrl());
            addToPurchasedBooks(book);
        }
    }

    private boolean isBookValid(Book book) {
        return book.getBookUrl() != null;
    }

    private void addToPurchasedBooks(Book book) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                         .putBoolean(book.getBookUrl(), true)
                         .apply();
    }

    private void removeFromPurchasedBooks(Book book) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                         .remove(book.getBookUrl())
                         .apply();
    }

    private boolean isAlreadyPurchased(Book book) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.contains(book.getBookUrl());
    }

    public void returnBook(Book book) {
        if (isBookValid(book) && isAlreadyPurchased(book)) {
            BillingService.startActionReturn(context, book.getBookUrl());
            removeFromPurchasedBooks(book);
        }
    }
}
