package com.develop.vic.quiz.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

import com.develop.vic.quiz.database.QuizDB;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Created by Victor on 9/6/16.
 */
public class CustomCursorLoader   extends AsyncTaskLoader<Cursor> {
    final ForceLoadContentObserver mObserver;
    BaseModelQueriable<? extends QuizDB> mQuery;
    Cursor mCursor;
    CancellationSignal mCancellationSignal;
    Uri mUri;

    /* Runs on a worker thread */
    @Override
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            mCancellationSignal = new CancellationSignal();
        }
        try {
            Cursor cursor = mQuery.query();
            if (cursor != null) {
                try {
                    // Ensure the cursor window is filled.
                    cursor.getCount();
                    cursor.setNotificationUri(FlowManager.getContext().getContentResolver(), mUri);
                    cursor.registerContentObserver(mObserver);
                } catch (RuntimeException ex) {
                    cursor.close();
                    throw ex;
                }
            }
            return cursor;
        } finally {
            synchronized (this) {
                mCancellationSignal = null;
            }
        }
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();

        synchronized (this) {
            if (mCancellationSignal != null) {
                mCancellationSignal.cancel();
            }
        }
    }

    /* Runs on the UI thread */
    @Override
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            // An async query came in while the loader is stopped
            if (cursor != null) {
                cursor.close();
            }
            return;
        }
        Cursor oldCursor = mCursor;
        mCursor = cursor;

        if (isStarted()) {
            super.deliverResult(cursor);
        }

        if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    /**
     * Creates not-initialised CursorLoader. You should call setContentUri() and setQuery() later
     */
    public CustomCursorLoader(Context context) {
        super(context);
        mObserver = new ForceLoadContentObserver();
    }

    /**
     * Creates a fully-specified CursorLoader.  See
     * {@link ContentResolver#query(Uri, String[], String, String[], String)
     * ContentResolver.query()} for documentation on the meaning of the
     * parameters.  These will be passed as-is to that call.
     */
    public CustomCursorLoader(Context context, Uri contentUri, BaseModelQueriable<? extends QuizDB> query) {
        super(context);
        mObserver = new ForceLoadContentObserver();
        mUri = contentUri;
        mQuery = query;
    }

    /**
     * Sets content uri for the resulting cursor. Should be called before first load.
     *
     * @param contentUri - uri which will be notified of content changes so this loader could restart itself
     */
    public void setContentUri(Uri contentUri) {
        mUri = contentUri;
    }

    /**
     * Sets query. Should be called before first load.
     *
     * @param query - query expression
     */
    public void setQuery(BaseModelQueriable<? extends QuizDB> query) {
        mQuery = query;
    }

    public BaseModelQueriable<? extends QuizDB> getQuery() {
        return mQuery;
    }

    /**
     * Starts an asynchronous load of the contacts list data. When the result is ready the callbacks
     * will be called on the UI thread. If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * <p/>
     * Must be called from the UI thread
     */
    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        mCursor = null;
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("mUri=");
        writer.println(mUri.toString());
        writer.print(prefix);
        writer.print("mQuery=");
        writer.println(mQuery.getQuery());
    }
}