package sagarb.grape;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

  public static final int CAMERA_REQUEST_CODE = 1;
  private SurfaceView camView;
  private CameraSource cameraSource;
  private BarcodeDetector barcodeDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //Remove title bar
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    //Remove notification bar
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    camView = (SurfaceView)findViewById(R.id.camera_view);
    barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

    cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640,480).build();
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
      }
    } else {
      initializeCamView(barcodeDetector);
    }
  }


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case CAMERA_REQUEST_CODE: {
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          initializeCamView(barcodeDetector);
        } else {
          finish();
        }
      }
    }
  }

  private void initializeCamView(final BarcodeDetector barcodeDetector) {
    camView.getHolder().addCallback(new SurfaceHolder.Callback() {
      @Override
      public void surfaceCreated(SurfaceHolder holder) {
        try {
          cameraSource.start(camView.getHolder());
        } catch (IOException ie) {
          Log.e("CAMERA SOURCE", ie.getMessage());
        }
        camView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            cameraFocus(cameraSource, Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
          }
        });
      }

      @Override
      public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
      }

      @Override
      public void surfaceDestroyed(SurfaceHolder holder) {
        cameraSource.stop();
      }
    });
    barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {


      @Override
      public void release() {
      }

      @Override
      public void receiveDetections(Detector.Detections<Barcode> detections) {
        SparseArray<Barcode> code = detections.getDetectedItems();
        if (code.size() != 0) {
          GlobalVar.URL = code.valueAt(0).displayValue;
          runOnUiThread(new Runnable() {
            public void run() {

              Toast.makeText(MainActivity.this, "URL scanned :\n" + GlobalVar.URL, Toast.LENGTH_SHORT).show();
              Intent success = new Intent(MainActivity.this, sagarb.grape.success.class);
              startActivity(success);
            }
          });
          barcodeDetector.release();
        }
      }
    });
  }

  private static boolean cameraFocus(@NonNull CameraSource cameraSource, @NonNull String focusMode) {
    Field[] declaredFields = CameraSource.class.getDeclaredFields();

    for (Field field : declaredFields) {
      if (field.getType() == Camera.class) {
        field.setAccessible(true);
        try {
          Camera camera = (Camera) field.get(cameraSource);
          if (camera != null) {
            Camera.Parameters params = camera.getParameters();
            params.setFocusMode(focusMode);
            camera.setParameters(params);
            return true;
          }

          return false;
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }

        break;
      }
    }

    return false;
  }
}

