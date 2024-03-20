package com.example.oucinema;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        new NavBarHeader().run();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        // Cập nhật giao diện người dùng (nếu cần)
    }

    @Override
    protected void onPostExecute(Void result) {
        // Cập nhật giao diện người dùng sau khi tác vụ hoàn thành (nếu cần)
    }
}