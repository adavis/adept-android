package info.adavis.adeptandroid.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import timber.log.Timber;

public class BillingService extends IntentService {

    private static final String ACTION_PURCHASE = "info.adavis.adeptandroid.action.PURCHASE";
    private static final String ACTION_RETURN = "info.adavis.adeptandroid.action.RETURN";
    private static final String EXTRA_BOOK_URL = "info.adavis.adeptandroid.extra.EXTRA_BOOK_URL";

    public static void startActionPurchase(Context context, String bookUrl) {
        Intent intent = new Intent(context, BillingService.class);
        intent.setAction(ACTION_PURCHASE);
        intent.putExtra(EXTRA_BOOK_URL, bookUrl);
        context.startService(intent);
    }

    public static void startActionReturn(Context context, String bookUrl) {
        Intent intent = new Intent(context, BillingService.class);
        intent.setAction(ACTION_RETURN);
        intent.putExtra(EXTRA_BOOK_URL, bookUrl);
        context.startService(intent);
    }

    public BillingService() {
        super("BillingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PURCHASE.equals(action)) {
                final String bookUrl = intent.getStringExtra(EXTRA_BOOK_URL);
                handleActionPurchase(bookUrl);
            }
            else {
                final String bookUrl = intent.getStringExtra(EXTRA_BOOK_URL);
                handleActionReturn(bookUrl);
            }
        }
    }

    private void handleActionPurchase(String bookUrl) {
        Timber.i("purchased book: %s", bookUrl);
    }

    private void handleActionReturn(String bookUrl) {
        Timber.i("returned book: %s", bookUrl);
    }

}
