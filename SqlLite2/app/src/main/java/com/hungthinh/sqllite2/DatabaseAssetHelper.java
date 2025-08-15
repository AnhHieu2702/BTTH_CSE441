package com.hungthinh.sqllite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseAssetHelper {

    // ĐÚNG tên file trong assets/
    public static final String DATABASE_NAME = "qlsach.db";
    private static final String DB_FOLDER_SUFFIX = "/databases/";

    private final Context context;

    public DatabaseAssetHelper(Context context) {
        this.context = context;
    }

    private File dbFile() {
        return new File(context.getApplicationInfo().dataDir + DB_FOLDER_SUFFIX + DATABASE_NAME);
    }

    /** Copy DB từ assets vào /data/data/<pkg>/databases nếu chưa có */
    public void copyIfNeeded() {
        File outFile = dbFile();
        if (outFile.exists()) return;

        if (outFile.getParentFile() != null) {
            outFile.getParentFile().mkdirs();
        }

        try (InputStream input = context.getAssets().open(DATABASE_NAME);
             FileOutputStream output = new FileOutputStream(outFile)) {

            byte[] buf = new byte[8 * 1024];
            int r;
            while ((r = input.read(buf)) != -1) {
                output.write(buf, 0, r);
            }
            output.flush();
            Log.i("DB", "Copied to " + outFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Mở và trả về SQLiteDatabase */
    public SQLiteDatabase openDatabase() {
        copyIfNeeded();
        return SQLiteDatabase.openDatabase(
                dbFile().getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE
        );
    }
}
