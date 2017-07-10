package com.example.mydaily;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {

    public static final int ITEM_DIR = 0;

    public static final int ITEM = 1;

    public static final String AUTHORITY ="com.example.mydaily.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contact", ITEM_DIR);
        uriMatcher.addURI(AUTHORITY, "contact/#", ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "Item.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
        case ITEM_DIR:
            cursor = db.query("contact", projection, selection, selectionArgs, null, null, sortOrder);
            break;
        case ITEM:
            String bookId = uri.getPathSegments().get(1);
            cursor = db.query("contact", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
            break;
        default:
            break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
        case ITEM_DIR:
        case ITEM:
            long newItemId = db.insert("contact", null, values);
            uriReturn = Uri.parse("content://" + AUTHORITY + "/contact/" + newItemId);
            break;
        default:
            break;
        }
        return uriReturn;
    }

   
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        case ITEM_DIR:
            return "vnd.android.cursor.dir/vnd.com.example.mydaily.provider.contact";
        case ITEM:
            return "vnd.android.cursor.item/vnd.com.example.mydaily.provider.contact";
        }
        return null;
    }

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}