package org.idnp.activityforresultsample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtMensaje = findViewById(R.id.txtMensaje);
        imageView = findViewById(R.id.imageView);

        Button btnOpenActivityOld = findViewById(R.id.btnOpenActivityOld);
        Button btnOpenActivityCurrent = findViewById(R.id.btnOpenActivityCurrent);
        Button btnTakePicturePreview = findViewById(R.id.btnTakePicturePreview);
        Button btnImage = findViewById(R.id.btnImage);

        btnTakePicturePreview.setOnClickListener(onClickListenerTakePicturePreview);
        btnOpenActivityCurrent.setOnClickListener(onClickListenerIntent);
        btnImage.setOnClickListener(onClickListenerImage);
        btnOpenActivityOld.setOnClickListener(onClickListenerOpenActivityOld);

    }

    private View.OnClickListener onClickListenerOpenActivityOld=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(intent, 2);
        }
    };

    private View.OnClickListener onClickListenerTakePicturePreview = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            activityResultLauncherTakePicturePreview.launch(null);
        }
    };

    private View.OnClickListener onClickListenerImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            activityResultLauncherString.launch("image/*");
        }
    };

    private View.OnClickListener onClickListenerIntent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent secondActivityIntent =
                    new Intent(MainActivity.this, SecondActivity.class);

            activityResultLauncherIntent.launch(secondActivityIntent);
        }
    };


    private ActivityResultLauncher<Void> activityResultLauncherTakePicturePreview = registerForActivityResult(
            new ActivityResultContracts.TakePicturePreview(),
            new ActivityResultCallback<Bitmap>() {
                @Override
                public void onActivityResult(Bitmap result) {
                    imageView.setImageBitmap(result);
                }
            }
    );

    private ActivityResultLauncher<String> activityResultLauncherString = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    Log.d(TAG, result.getPath());
                }
            }
    );

    private ActivityResultLauncher<Intent> activityResultLauncherIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "ResultCode:" + result.getResultCode());
                    Intent data = result.getData();
                    String message = data.getStringExtra(SecondActivity.MESSAGE);
                    Log.d(TAG, message);
                }
            });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            String message = data.getStringExtra(SecondActivity.MESSAGE);
            Log.d(TAG, message);
        } else if (requestCode == 3) {

        }

    }

}