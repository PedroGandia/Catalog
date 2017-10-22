package android.test.catalog.mainscreen.loader;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;
import android.test.catalog.data.local.DatabaseContract;
import android.test.catalog.data.local.DatabaseHelper;
import android.test.catalog.data.local.models.Data;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class DataLoader extends AsyncTaskLoader<Data> {

  private static final String TAG = DataLoader.class.getSimpleName();


  private Context context;
  private String appId;
  private Data data;

  public DataLoader(Context context, String appId){
    super(context);
    this.context = context;
    this.appId = appId;
  }

  @Override
  public void deliverResult(Data data) {
    if (isReset()) {
      // An async query came in while the loader is stopped
      return;
    }

    this.data = data;

    super.deliverResult(data);
  }


  @Override
  protected void onStartLoading() {
    if (data != null) {
      deliverResult(data);
    }

    if (takeContentChanged() || data == null) {
      forceLoad();
    }
  }

  @Override
  protected void onStopLoading() {
    // Attempt to cancel the current load task if possible.
    cancelLoad();
  }

  @Override
  protected void onReset() {
    super.onReset();

    // Ensure the loader is stopped
    onStopLoading();

    data = null;
  }



  @Override
  public Data loadInBackground() {

    try {
        data = new Data();
        if (appId != null && !appId.isEmpty()) {
          data = getDataByAddId(appId);
        }

    } catch (Exception e) {
      e.getMessage();
    }

    return data;
  }

  public Data getDataByAddId(String appId) {
    DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
    SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

    Data data = new Data();

    String selectQuery = "SELECT * FROM " + DatabaseContract.Data.TABLE_NAME +
            " WHERE " + DatabaseContract.Data.COLUMN_ID + "= '" + appId +"' ";
    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_ID)) != null){
          data.setId(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_ID)));
        }else{
          data.setId("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_DISPLAY_NAME)) != null){
          data.setDisplayName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_DISPLAY_NAME)));
        }else{
          data.setDisplayName("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_HEADER_IMG)) != null){
          data.setHeaderImg(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_HEADER_IMG)));
        }else{
          data.setHeaderImg("");
        }


        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)) != null){
          data.setAudienceTarget(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)));
        }else{
          data.setAudienceTarget("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_HEADER_TITLE)) != null){
          data.setHeaderTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_HEADER_TITLE)));
        }else{
          data.setHeaderTitle("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_DESCRIPTION)) != null){
          data.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_DESCRIPTION)));
        }else{
          data.setDescription("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_KEY_COLOR)) != null){
          data.setKeyColor(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_KEY_COLOR)));
        }else{
          data.setKeyColor("");
        }

        if (cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_ICON_IMG)) != null){
          data.setIconImg(cursor.getString(cursor.getColumnIndex(DatabaseContract.Data.COLUMN_ICON_IMG)));
        }else{
          data.setIconImg("");
        }


      }
      cursor.close();
    }else{
      data = null;
    }



    return data;
  }

}
