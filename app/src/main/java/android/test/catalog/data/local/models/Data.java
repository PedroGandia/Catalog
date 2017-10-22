package android.test.catalog.data.local.models;

import android.test.catalog.data.local.DatabaseContract;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import java.util.List;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

@StorIOSQLiteType(table = DatabaseContract.Data.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Data.CONTENT_URI_STRING)
public class Data {

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_ID, key = true)
    public String id;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_DISPLAY_NAME)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_DISPLAY_NAME)
    public String display_name;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_HEADER_IMG)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_HEADER_IMG)
    public String header_img;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)
    public String audience_target;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_ICON_IMG)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_ICON_IMG)
    public String icon_img;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_HEADER_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_HEADER_TITLE)
    public String header_title;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_DESCRIPTION)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_DESCRIPTION)
    public String description;

    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_KEY_COLOR)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_KEY_COLOR)
    public String key_color;


    private List<DataResponse.Children> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String display_name) {
        this.display_name = display_name;
    }

    public String getHeaderImg() {
        return header_img;
    }

    public void setHeaderImg(String header_img) {
        this.header_img = header_img;
    }

    public String getAudienceTarget() {
        return audience_target;
    }

    public void setAudienceTarget(String audience_target) {
        this.audience_target = audience_target;
    }

    public String getIconImg() {
        return icon_img;
    }

    public void setIconImg(String icon_img) {
        this.icon_img = icon_img;
    }

    public String getHeaderTitle() {
        return header_title;
    }

    public void setHeaderTitle(String header_title) {
        this.header_title = header_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyColor() {
        return key_color;
    }

    public void setKeyColor(String key_color) {
        this.key_color = key_color;
    }

    public List<DataResponse.Children> getChildren() {
        return children;
    }

    public void setChildren(List<DataResponse.Children> children) {
        this.children = children;
    }

}
