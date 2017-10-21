package android.test.catalog.data.local.models;

import android.test.catalog.data.local.DatabaseContract;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import java.util.List;

@StorIOSQLiteType(table = DatabaseContract.Data.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Data.CONTENT_URI_STRING)
public class Data {

    private String banner_img;
    private boolean user_sr_theme_enabled;
    private String user_flair_text;
    private String submit_text_html;
    private String user_is_banned;
    private boolean wiki_enabled;
    private boolean show_media;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_ID, key = true)
    public String id;
    private String display_name_prefixed;
    private String submit_text;
    private String user_can_flair_in_sr;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_DISPLAY_NAME)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_DISPLAY_NAME)
    public String display_name;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_HEADER_IMG)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_HEADER_IMG)
    public String header_img;
    private String description_html;
    private String title;
    private boolean collapse_deleted_comments;
    private String user_has_favorited;
    private boolean over18;
    private String public_description_html;
    private boolean spoilers_enabled;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_AUDIENCE_TARGET)
    public String audience_target;
    private String suggested_comment_sort;
    private String active_user_count;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_ICON_IMG)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_ICON_IMG)
    public String icon_img;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_HEADER_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_HEADER_TITLE)
    public String header_title;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_DESCRIPTION)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_DESCRIPTION)
    public String description;
    private String user_is_muted;
    private String submit_link_label;
    private String accounts_active;
    private boolean public_traffic;
    private int subscribers;
    private String user_flair_css_class;
    private String submit_text_label;
    private String whitelist_status;
    private String user_sr_flair_enabled;
    private String lang;
    private String user_is_moderator;
    private String is_enrolled_in_new_modmail;
    @StorIOSQLiteColumn(name = DatabaseContract.Data.COLUMN_KEY_COLOR)
    @StorIOContentResolverColumn(name = DatabaseContract.Data.COLUMN_KEY_COLOR)
    public String key_color;
    private String name;
    private boolean user_flair_enabled_in_sr;
    private double created;
    private String url;
    private boolean quarantine;
    private boolean hide_ads;
    private double created_utc;
    private String user_is_contributor;
    private boolean accounts_active_is_fuzzed;
    private String advertiser_category;
    private String public_description;
    private boolean link_flair_enabled;
    private boolean allow_images;
    private boolean show_media_preview;
    private int comment_score_hide_mins;
    private String subreddit_type;
    private String submission_type;
    private String user_is_subscriber;

    private String modhash;
    private List<DataResponse.Children> children;
    private String after;
    private String before;

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public boolean getUser_sr_theme_enabled() {
        return user_sr_theme_enabled;
    }

    public void setUser_sr_theme_enabled(boolean user_sr_theme_enabled) {
        this.user_sr_theme_enabled = user_sr_theme_enabled;
    }

    public String getUser_flair_text() {
        return user_flair_text;
    }

    public void setUser_flair_text(String user_flair_text) {
        this.user_flair_text = user_flair_text;
    }

    public String getSubmit_text_html() {
        return submit_text_html;
    }

    public void setSubmit_text_html(String submit_text_html) {
        this.submit_text_html = submit_text_html;
    }

    public String getUser_is_banned() {
        return user_is_banned;
    }

    public void setUser_is_banned(String user_is_banned) {
        this.user_is_banned = user_is_banned;
    }

    public boolean getWiki_enabled() {
        return wiki_enabled;
    }

    public void setWiki_enabled(boolean wiki_enabled) {
        this.wiki_enabled = wiki_enabled;
    }

    public boolean getShow_media() {
        return show_media;
    }

    public void setShow_media(boolean show_media) {
        this.show_media = show_media;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name_prefixed() {
        return display_name_prefixed;
    }

    public void setDisplay_name_prefixed(String display_name_prefixed) {
        this.display_name_prefixed = display_name_prefixed;
    }

    public String getSubmit_text() {
        return submit_text;
    }

    public void setSubmit_text(String submit_text) {
        this.submit_text = submit_text;
    }

    public String getUser_can_flair_in_sr() {
        return user_can_flair_in_sr;
    }

    public void setUser_can_flair_in_sr(String user_can_flair_in_sr) {
        this.user_can_flair_in_sr = user_can_flair_in_sr;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

    public String getDescription_html() {
        return description_html;
    }

    public void setDescription_html(String description_html) {
        this.description_html = description_html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCollapse_deleted_comments() {
        return collapse_deleted_comments;
    }

    public void setCollapse_deleted_comments(boolean collapse_deleted_comments) {
        this.collapse_deleted_comments = collapse_deleted_comments;
    }

    public String getUser_has_favorited() {
        return user_has_favorited;
    }

    public void setUser_has_favorited(String user_has_favorited) {
        this.user_has_favorited = user_has_favorited;
    }

    public boolean getOver18() {
        return over18;
    }

    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    public String getPublic_description_html() {
        return public_description_html;
    }

    public void setPublic_description_html(String public_description_html) {
        this.public_description_html = public_description_html;
    }

    public boolean getSpoilers_enabled() {
        return spoilers_enabled;
    }

    public void setSpoilers_enabled(boolean spoilers_enabled) {
        this.spoilers_enabled = spoilers_enabled;
    }

    public String getAudience_target() {
        return audience_target;
    }

    public void setAudience_target(String audience_target) {
        this.audience_target = audience_target;
    }

    public String getSuggested_comment_sort() {
        return suggested_comment_sort;
    }

    public void setSuggested_comment_sort(String suggested_comment_sort) {
        this.suggested_comment_sort = suggested_comment_sort;
    }

    public String getActive_user_count() {
        return active_user_count;
    }

    public void setActive_user_count(String active_user_count) {
        this.active_user_count = active_user_count;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public String getHeader_title() {
        return header_title;
    }

    public void setHeader_title(String header_title) {
        this.header_title = header_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_is_muted() {
        return user_is_muted;
    }

    public void setUser_is_muted(String user_is_muted) {
        this.user_is_muted = user_is_muted;
    }

    public String getSubmit_link_label() {
        return submit_link_label;
    }

    public void setSubmit_link_label(String submit_link_label) {
        this.submit_link_label = submit_link_label;
    }

    public String getAccounts_active() {
        return accounts_active;
    }

    public void setAccounts_active(String accounts_active) {
        this.accounts_active = accounts_active;
    }

    public boolean getPublic_traffic() {
        return public_traffic;
    }

    public void setPublic_traffic(boolean public_traffic) {
        this.public_traffic = public_traffic;
    }


    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public String getUser_flair_css_class() {
        return user_flair_css_class;
    }

    public void setUser_flair_css_class(String user_flair_css_class) {
        this.user_flair_css_class = user_flair_css_class;
    }

    public String getSubmit_text_label() {
        return submit_text_label;
    }

    public void setSubmit_text_label(String submit_text_label) {
        this.submit_text_label = submit_text_label;
    }

    public String getWhitelist_status() {
        return whitelist_status;
    }

    public void setWhitelist_status(String whitelist_status) {
        this.whitelist_status = whitelist_status;
    }

    public String getUser_sr_flair_enabled() {
        return user_sr_flair_enabled;
    }

    public void setUser_sr_flair_enabled(String user_sr_flair_enabled) {
        this.user_sr_flair_enabled = user_sr_flair_enabled;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUser_is_moderator() {
        return user_is_moderator;
    }

    public void setUser_is_moderator(String user_is_moderator) {
        this.user_is_moderator = user_is_moderator;
    }

    public String getIs_enrolled_in_new_modmail() {
        return is_enrolled_in_new_modmail;
    }

    public void setIs_enrolled_in_new_modmail(String is_enrolled_in_new_modmail) {
        this.is_enrolled_in_new_modmail = is_enrolled_in_new_modmail;
    }

    public String getKey_color() {
        return key_color;
    }

    public void setKey_color(String key_color) {
        this.key_color = key_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getUser_flair_enabled_in_sr() {
        return user_flair_enabled_in_sr;
    }

    public void setUser_flair_enabled_in_sr(boolean user_flair_enabled_in_sr) {
        this.user_flair_enabled_in_sr = user_flair_enabled_in_sr;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
    }

    public boolean getHide_ads() {
        return hide_ads;
    }

    public void setHide_ads(boolean hide_ads) {
        this.hide_ads = hide_ads;
    }

    public double getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(double created_utc) {
        this.created_utc = created_utc;
    }

    public String getUser_is_contributor() {
        return user_is_contributor;
    }

    public void setUser_is_contributor(String user_is_contributor) {
        this.user_is_contributor = user_is_contributor;
    }

    public boolean getAccounts_active_is_fuzzed() {
        return accounts_active_is_fuzzed;
    }

    public void setAccounts_active_is_fuzzed(boolean accounts_active_is_fuzzed) {
        this.accounts_active_is_fuzzed = accounts_active_is_fuzzed;
    }

    public String getAdvertiser_category() {
        return advertiser_category;
    }

    public void setAdvertiser_category(String advertiser_category) {
        this.advertiser_category = advertiser_category;
    }

    public String getPublic_description() {
        return public_description;
    }

    public void setPublic_description(String public_description) {
        this.public_description = public_description;
    }

    public boolean getLink_flair_enabled() {
        return link_flair_enabled;
    }

    public void setLink_flair_enabled(boolean link_flair_enabled) {
        this.link_flair_enabled = link_flair_enabled;
    }

    public boolean getAllow_images() {
        return allow_images;
    }

    public void setAllow_images(boolean allow_images) {
        this.allow_images = allow_images;
    }

    public boolean getShow_media_preview() {
        return show_media_preview;
    }

    public void setShow_media_preview(boolean show_media_preview) {
        this.show_media_preview = show_media_preview;
    }

    public int getComment_score_hide_mins() {
        return comment_score_hide_mins;
    }

    public void setComment_score_hide_mins(int comment_score_hide_mins) {
        this.comment_score_hide_mins = comment_score_hide_mins;
    }

    public String getSubreddit_type() {
        return subreddit_type;
    }

    public void setSubreddit_type(String subreddit_type) {
        this.subreddit_type = subreddit_type;
    }

    public String getSubmission_type() {
        return submission_type;
    }

    public void setSubmission_type(String submission_type) {
        this.submission_type = submission_type;
    }

    public String getUser_is_subscriber() {
        return user_is_subscriber;
    }

    public void setUser_is_subscriber(String user_is_subscriber) {
        this.user_is_subscriber = user_is_subscriber;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<DataResponse.Children> getChildren() {
        return children;
    }

    public void setChildren(List<DataResponse.Children> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }


}
