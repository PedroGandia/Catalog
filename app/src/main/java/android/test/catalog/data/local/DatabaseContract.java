package android.test.catalog.data.local;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class DatabaseContract {

    public static final String CONTENT_AUTHORITY = "android.test.catalog";
    private static final String CONTENT_SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    public static final String PATH_DATA = "data";

    public DatabaseContract() {
    }

    public static abstract class Data implements BaseColumns {
        @NonNull
        public static final String CONTENT_URI_STRING = "content://" + CONTENT_AUTHORITY + "/" + PATH_DATA;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

        public static final String CONTENT_USER_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_DATA;
        public static final String CONTENT_USER_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_DATA;

        public static final String TABLE_NAME = "data";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DISPLAY_NAME = "display_name";
        public static final String COLUMN_HEADER_IMG = "header_img";
        public static final String COLUMN_AUDIENCE_TARGET = "audience_target";
        public static final String COLUMN_HEADER_TITLE = "header_title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_KEY_COLOR = "key_color";
        public static final String COLUMN_ICON_IMG = "icon_img";

        public static String getDataCreateQuery() {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " TEXT NOT NULL PRIMARY KEY, " +
                    COLUMN_DISPLAY_NAME + " TEXT, " +
                    COLUMN_HEADER_IMG + " TEXT, " +
                    COLUMN_AUDIENCE_TARGET + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_KEY_COLOR + " TEXT, " +
                    COLUMN_HEADER_TITLE + " TEXT, " +
                    COLUMN_ICON_IMG + " TEXT" + ");";
        }

        public static String getUserDeleteQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }


        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
