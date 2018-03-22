package com.ryan.utilslibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ryan on 18-3-17.
 * Email: Ryan_chan01212@yeah.net
 */

public class AssetsManager {
    private static final String TAG = AssetsManager.class.getSimpleName();
    //    private static final String ASSET_LIST_FILENAME = "assets.lst";
    private Context mContext;
    private AssetManager mAssetManager;

    public AssetsManager(Context context) {
        super();
        mContext = context;
        mAssetManager = context.getAssets();
    }

    /**
     * @param assetsPath asset下的路径
     * @param savePath   SD卡下保存路径
     * @return
     * @error
     */
    public void copyFilesFromAssets(String assetsPath, String savePath) throws IOException {
        try {
            // 获取assets目录下的所有文件及目录名
            String fileNames[] = mAssetManager.list(assetsPath);
            Log.i(TAG, "copyFilesFromAssets: fileNames.length = " + fileNames.length);
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();// 如果文件夹不存在，则递归
                }
                for (String fileName : fileNames) {
                    Log.i(TAG, "copyFilesFromAssets: assetsPath = " + assetsPath + "/" + fileName);
                    copyFilesFromAssets(assetsPath + "/" + fileName,
                            savePath + "/" + fileName);
                }
            } else {
                Log.i(TAG, "copyFilesFromAssets: savaPath = " + new File(savePath).getPath());
                Log.i(TAG, "copyFilesFromAssets: assetsPath = " + assetsPath);
                if (!new File(savePath, assetsPath).exists()) {
                    Log.i(TAG, "copyFilesFromAssets: not exits");
                    InputStream is = mAssetManager.open(assetsPath);
                    FileOutputStream fos = new FileOutputStream(new File(savePath));

                    byte[] buffer = new byte[1024];
                    int byteCount = 0;
                    while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                        fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                    }
                    fos.flush();// 刷新缓冲区
                    is.close();
                    fos.close();
                    Log.i(TAG, "copyFilesFromAssets: CopyRight");
                } else {
                    Log.i(TAG, "copyFilesFromAssets: exits");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void release() {
        mAssetManager = null;
        mContext = null;
    }
}
