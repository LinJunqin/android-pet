package com.lin.person.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by LIN on 2017/4/23.
 */

public class PhotoUtil {
    public  static  int calculateSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final  int height = options.outHeight;
        final  int width = options.outWidth;
        int inSampleSize =1;
        if(height>reqHeight ||width>reqWidth){
            final  int heightRatio = Math.round((float)height/(float)reqHeight);
            final  int widthRatio = Math.round((float)width/(float)reqWidth);
            inSampleSize = heightRatio<widthRatio ? heightRatio:widthRatio;
        }
        return inSampleSize;
    }
    public static Bitmap getSmallBitmap(Activity ac,Uri uri)  {
        InputStream input =null;
        try {
           input = ac.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds =true;
        BitmapFactory.decodeStream(input,null,options);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options.inSampleSize = calculateSampleSize(options,480,480);
        options.inJustDecodeBounds = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            input = ac.getContentResolver().openInputStream(uri);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(input,null,options).compress(Bitmap.CompressFormat.PNG,40,baos);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

           Bitmap bm =    BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bm;
    }
    public static Bitmap decodeUriAsBitmap(Activity ac,Uri imageUri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(ac.getContentResolver().openInputStream(imageUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
    public static void cropImageUri(Activity ac,Uri uri, int outputX, int outputY, int requestCode) {
        File CropPhoto = new File(ac.getExternalCacheDir(), "Crop.png");//这个是创建一个截取后的图片路径和名称。
        try {
            if (CropPhoto.exists()) {
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imageUri = Uri.fromFile(CropPhoto);
        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 2);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        ac.startActivityForResult(intent, requestCode);
    }
    public static Uri bitmap2Uri(Context ac, String imageName, Bitmap bitmap){
        File imageFile= new File(ac.getExternalCacheDir(),imageName);
        FileOutputStream fout = null;
        if(imageFile.exists()){
            imageFile.delete();
        }
        try {
            imageFile.createNewFile();
            fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fout);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return  getImageContentUri(ac, imageFile);
        }
      return  Uri.fromFile(imageFile);
    }
    //获取文件的Content uri路径
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
