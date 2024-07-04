package nso.hh2568.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {

	public static void createPopup(Context context, String MSG) {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
		helpBuilder.setTitle("แจ้งเตือน");
		helpBuilder.setMessage(MSG);
		helpBuilder.setCancelable(false);
		helpBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		helpBuilder.show();
	}

	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	
	   public static boolean isNetworkAvailable(Context context) {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null;
		}
	   
	   public static byte[] getCompressImage(String url){
		   
			try{
				Bitmap image = BitmapFactory.decodeFile(url);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				image.compress(Bitmap.CompressFormat.JPEG, 20, out);
				
				return out.toByteArray();
			}catch(Exception ex){}
			
		     return null;
		}
	   
	public static String getEif(String path){
			
			ExifInterface exif;
			try {
				exif = new ExifInterface(path);
				
				/*
				StringBuilder builder = new StringBuilder();
				 builder.append("Date & Time: " + exif.getAttribute(ExifInterface.TAG_DATETIME) + "\n\n");
				 builder.append("Flash: " + exif.getAttribute(ExifInterface.TAG_FLASH) + "\n");
				 builder.append("Focal Length: " + exif.getAttribute(ExifInterface.TAG_FOCAL_LENGTH) + "\n\n");
				 builder.append("GPS Datestamp: " + exif.getAttribute(ExifInterface.TAG_FLASH) + "\n");
				 builder.append("GPS Latitude: " + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) + "\n");
				 builder.append("GPS Latitude Ref: " + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF) + "\n");
				 builder.append("GPS Longitude: " + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) + "\n");
				builder.append("GPS Longitude Ref: " + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF) + "\n");
				 builder.append("GPS Processing Method: " + exif.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD) + "\n");
				 builder.append("GPS Timestamp: " + exif.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP) + "\n\n");
				builder.append("Image Length: " + exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) + "\n");
				builder.append("Image Width: " + exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) + "\n\n");
				builder.append("Camera Make: " + exif.getAttribute(ExifInterface.TAG_MAKE) + "\n");
				builder.append("Camera Model: " + exif.getAttribute(ExifInterface.TAG_MODEL) + "\n");
				builder.append("Camera Orientation: " + exif.getAttribute(ExifInterface.TAG_ORIENTATION) + "\n");
				builder.append("Camera White Balance: " + exif.getAttribute(ExifInterface.TAG_WHITE_BALANCE) + "\n");  
				Log.e("tag", builder.toString());
				*/
				String data =convertToDegree(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)) +',' + convertToDegree(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)) +',' + exif.getAttribute(ExifInterface.TAG_GPS_DATESTAMP);
				Log.e("tag", data.toString());
				 return data.replace("null","");
			} catch (IOException e) {
				 e.printStackTrace();
				 return "";
			}
			 
			
		}
	
	private static String convertToDegree(String stringDMS){
		if(stringDMS !=null){
            Float result = null;
            String[] DMS = stringDMS.split(",", 3);

            String[] stringD = DMS[0].split("/", 2);
               Double D0 = new Double(stringD[0]);
               Double D1 = new Double(stringD[1]);
               Double FloatD = D0/D1;

            String[] stringM = DMS[1].split("/", 2);
            Double M0 = new Double(stringM[0]);
            Double M1 = new Double(stringM[1]);
            Double FloatM = M0/M1;
            
            String[] stringS = DMS[2].split("/", 2);
            Double S0 = new Double(stringS[0]);
            Double S1 = new Double(stringS[1]);
            Double FloatS = S0/S1;
            
               result = new Float(FloatD + (FloatM/60) + (FloatS/3600));
               
            return result.toString();
		}
		else{
			return "";
		}
       };
}
