package android.test.catalog.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class Provider extends ContentProvider {

    private static final int DATA_ITEM = 100;
    private static final int DATA_DIR = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DatabaseHelper mDbHelper;


    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DatabaseContract.PATH_DATA + "/#", DATA_ITEM);
        matcher.addURI(authority, DatabaseContract.PATH_DATA, DATA_DIR);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case DATA_ITEM:
                retCursor = mDbHelper.getReadableDatabase().query(
                        DatabaseContract.Data.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case DATA_DIR:
                retCursor = mDbHelper.getReadableDatabase().query(
                        DatabaseContract.Data.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA_ITEM:
                return DatabaseContract.Data.CONTENT_USER_ITEM_TYPE;
            case DATA_DIR:
                return DatabaseContract.Data.CONTENT_USER_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            //Case for data
            case DATA_DIR:
                long _id = db.insert(DatabaseContract.Data.TABLE_NAME, null, contentValues);
                if (_id > 0)
                    returnUri = DatabaseContract.Data.buildUserUri(_id);
                else
                    throw new SQLException("Failed to insert row " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        switch (sUriMatcher.match(uri)) {
            case DATA_DIR:
                rowsDeleted = db.delete(DatabaseContract.Data.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }
        if (selection == null || 0 != rowsDeleted)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int update;
        switch (sUriMatcher.match(uri)) {
            //Case for User
            case DATA_DIR:
                update = db.update(DatabaseContract.Data.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }
        if (update > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return update;
    }
}
