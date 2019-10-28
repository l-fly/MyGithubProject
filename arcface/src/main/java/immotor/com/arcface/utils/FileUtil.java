package immotor.com.arcface.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static boolean saveView(byte[] nv21,Camera camera, Rect rect, String name) {
        boolean isSave = true;
        Bitmap bitmap = getBitMap(nv21,camera,false);
        sava(bitmap,name);
        Log.i("###","bitmap   " + bitmap.getWidth() +"    " +  bitmap.getHeight());
        Bitmap bitmap2 = cropBitmapCustom(bitmap,rect);
        sava(bitmap2,"#" + name);

        return isSave;
    }
    private static boolean sava(Bitmap bitmap,String name){
        boolean isSave;
        String filePath = Environment.getExternalStorageDirectory()+ File.separator + "face" ;
        Log.i("####",filePath);
        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdir();
        }
        FileOutputStream outputStream = null;
        try {
            String fileName = dir.toString() + File.separator + name;
            outputStream = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            isSave = true;
        } catch (IOException e) {
            isSave = false;
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSave;
    }
    public static Bitmap getBitMap(byte[] data, Camera camera, boolean mIsFrontalCamera) {
        int width = camera.getParameters().getPreviewSize().width;
        int height = camera.getParameters().getPreviewSize().height;
        YuvImage yuvImage = new YuvImage(data, camera.getParameters()
                .getPreviewFormat(), width, height, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, width, height), 80,
                byteArrayOutputStream);
        byte[] jpegData = byteArrayOutputStream.toByteArray();
        // 获取照相后的bitmap
        Bitmap tmpBitmap = BitmapFactory.decodeByteArray(jpegData, 0,
                jpegData.length);
        Matrix matrix = new Matrix();
        matrix.reset();
       /* if (mIsFrontalCamera) {
            matrix.setRotate(-90);
        } else {
            matrix.setRotate(90);
        }*/
        tmpBitmap = Bitmap.createBitmap(tmpBitmap, 0, 0, tmpBitmap.getWidth(),
                tmpBitmap.getHeight(), matrix, true);
        tmpBitmap = tmpBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int hight = tmpBitmap.getHeight() > tmpBitmap.getWidth() ? tmpBitmap
                .getHeight() : tmpBitmap.getWidth();

       /* float scale = hight / 800.0f;

        if (scale > 1) {
            tmpBitmap = Bitmap.createScaledBitmap(tmpBitmap,
                    (int) (tmpBitmap.getWidth() / scale),
                    (int) (tmpBitmap.getHeight() / scale), false);
        }*/
        return tmpBitmap;
    }
    private static Bitmap camera2Batmap(byte[] nv21,Camera camera){
        try {
            //格式成YUV格式
            YuvImage yuvimage = new YuvImage(nv21, ImageFormat.NV21, camera.getParameters().getPreviewSize().width,
                    camera.getParameters().getPreviewSize().height, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(new Rect(0, 0, camera.getParameters().getPreviewSize().width,
                    camera.getParameters().getPreviewSize().height), 100, baos);
            Bitmap bitmap = bytes2Bimap(baos.toByteArray());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    private static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }

    public static Bitmap convertViewToBitmap(View view){

        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        Bitmap bitmap=view.getDrawingCache();

        return bitmap;

    }
    public static Bitmap cropBitmapCustom(Bitmap srcBitmap,Rect rect) {
       /* int firstPixelX = 0;
        int firstPixelY = 0;
        int add = 40;
        if(rect.left > add){
            firstPixelX = rect.left -add;
        }
        if(rect.top > add){
            firstPixelY = rect.top -add;
        }
        int needWidth = rect.width() + add*2;
        int needHeight = rect.height() + add *2;
        if(firstPixelX + needWidth > srcBitmap.getWidth()){
            needWidth = srcBitmap.getWidth() - firstPixelX;
        }
        if(firstPixelY + needHeight > srcBitmap.getHeight()){
            needHeight = srcBitmap.getHeight() - firstPixelY;
        }*/
        int firstPixelX = rect.left;
        int firstPixelY = rect.top;
        int needWidth = rect.width();
        int needHeight = rect.height();
        /**裁剪关键步骤*/
        Bitmap cropBitmap = Bitmap.createBitmap(srcBitmap, firstPixelX, firstPixelY, needWidth, needHeight);
        Log.i("###","cropBitmap   " + cropBitmap.getWidth() +"    " +  cropBitmap.getHeight());
        return cropBitmap;
    }
}
