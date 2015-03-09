/*MainActivity.java
 * 
 * Contains java code for the Primary Activity in this project
 * 
 * Created by: Artur Lukin
 * Created on: 3/2/15
 * Last modified by: Artur Lukin
 * Last modified on: 3/6/15
 * Assignment/Project: LoudFlash
 * Refers to activity_main.xml, loudflash_about.xml
 */

package edu.indiana.loudflash;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements MicrophoneInputListener, OnClickListener {

	  MicrophoneInput micInput;  // The micInput object provides real time audio.
	  TextView mdBTextView;
	  TextView mdBFractionTextView;
	  SeekBar thresholdBar;
	  TextView thresholdText;

	  double mOffsetdB = 10;  // Offset for bar, i.e. 0 lit LEDs at 10 dB.
	  // The Google ASR input requirements state that audio input sensitivity
	  // should be set such that 90 dB SPL at 1000 Hz yields RMS of 2500 for
	  // 16-bit samples, i.e. 20 * log_10(2500 / mGain) = 90.
	  double mGain = 2500.0 / Math.pow(10.0, 90.0 / 20.0);
	  // For displaying error in calibration.
	  double mDifferenceFromNominal = 0.0;
	  double mRmsSmoothed;  // Temporally filtered version of RMS.
	  double mAlpha = 0.9;  // Coefficient of IIR smoothing filter for RMS.
	  private int mSampleRate;  // The audio sampling rate to use.
	  private int mAudioSource;  // The audio source to use.
	  int thresholdLevel;
	  
	

	private SpeedometerGauge speedometer;
    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Parameters params;
    ToggleButton button;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Here the micInput object is created for audio capture.
        // It is set up to call this object to handle real time audio frames of
        // PCM samples. The incoming frames will be handled by the
        // processAudioFrame method below.
        micInput = new MicrophoneInput(this);
        
        // Read the layout and construct.

        // Get a handle that will be used in async thread post to update the
        // display.
        mdBTextView = (TextView) findViewById(R.id.dBTextView);
        
        readPreferences();
        micInput.setSampleRate(mSampleRate);
        micInput.setAudioSource(mAudioSource);
        micInput.start();
        
        thresholdBar = (SeekBar) findViewById(R.id.threshold);
        thresholdText = (TextView) findViewById(R.id.thresholdText);
        thresholdText.setText("" + thresholdBar.getProgress());
        thresholdLevel = thresholdBar.getProgress();
        
        thresholdBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
            @Override       
            public void onStopTrackingTouch(SeekBar seekBar) {            
            }       
            @Override       
            public void onStartTrackingTouch(SeekBar seekBar) {     
            }       
            @Override       
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                thresholdText.setText("" + progress);
                thresholdLevel = progress;
                
            }
        });
        
        View AboutButton = findViewById(R.id.AboutButton); 
        AboutButton.setOnClickListener(this);

        final ToggleButton onOffButton=(ToggleButton)findViewById(
            R.id.on_off_toggle_button);

        ToggleButton.OnClickListener tbListener =
            new ToggleButton.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (onOffButton.isChecked()) {
              readPreferences();
              micInput.setSampleRate(mSampleRate);
              micInput.setAudioSource(mAudioSource);
              micInput.start();
            } else {
              micInput.stop();
            }
          }
        };
        onOffButton.setOnClickListener(tbListener);

        // Customize SpeedometerGauge
        speedometer = (SpeedometerGauge) findViewById(R.id.speedometer);
        
         //Add label converter
        speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        // configure value range and ticks
        speedometer.setMaxSpeed(120);
        speedometer.setMajorTickStep(10);
        speedometer.setMinorTicks(1);

        // Configure value range colors
        speedometer.addColoredRange(-20, 60, Color.argb(220, 65, 187, 233));
        speedometer.addColoredRange(60, 70, Color.parseColor("#ff6600"));
        speedometer.addColoredRange(70, 130, Color.RED);
 
 
        // First check if device is supporting flashlight or not        
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
 
        if (!hasFlash) {
            // device doesn't support flash     	
        	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error")
            .setMessage("Sorry, your device doesn't support LoudFlash!")
            .setCancelable(false)
            .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
 
        // get the camera
        getCamera();
             
    

    }
    
    public void onClick(View v) 
    { switch (v.getId()) {
	case R.id.AboutButton:
		Intent i = new Intent(this, LoudFlashAboutActivity.class); 
		startActivity(i);
		break;
		/*More buttons will/can go here as needed*/
	}
}
    
     

  /**
   * Method to read the sample rate and audio source preferences.
   */
  private void readPreferences() {
    SharedPreferences preferences = getSharedPreferences("LevelMeter",
        MODE_PRIVATE);
    mSampleRate = preferences.getInt("SampleRate", 8000);
    mAudioSource = preferences.getInt("AudioSource", 
        MediaRecorder.AudioSource.VOICE_RECOGNITION);
  }
  
  /**
   *  This method gets called by the micInput object owned by this activity.
   *  It first computes the RMS value and then it sets up a bit of
   *  code/closure that runs on the UI thread that does the actual drawing.
   */
  @Override
  public void processAudioFrame(short[] audioFrame) {

      // Compute the RMS value. (Note that this does not remove DC).
      double rms = 0;
      for (int i = 0; i < audioFrame.length; i++) {
        rms += audioFrame[i]*audioFrame[i];
      }
      rms = Math.sqrt(rms/audioFrame.length);

      // Compute a smoothed version for less flickering of the display.
      mRmsSmoothed = mRmsSmoothed * mAlpha + (1 - mAlpha) * rms;
      final double rmsdB = 20.0 * Math.log10(mGain * mRmsSmoothed);
      
      // Set up a method that runs on the UI thread to update of the LED bar
      // and numerical display.      
      mdBTextView.post(new Runnable() {
        @Override
        public void run() {

          //DecimalFormat df = new DecimalFormat("##");
          mdBTextView.setText("" + (int) (20 + rmsdB) + " dB");
          speedometer.setSpeed(Double.valueOf(20+rmsdB).doubleValue());//,20, 0);
          if ((20 + rmsdB) >= thresholdLevel) {
        	  //flashTest.setText("Flashlight ON");
        	  turnOnFlash();
          } else {
        	  //flashTest.setText("Flashlight OFF");
        	  turnOffFlash();
          }
         

        }
      });
  }    

// Get the camera
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {}
        }
    }
    
     // Turning On flash
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }
    }
    
    // Turning Off flash
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;  
        }
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
 
    @Override
    protected void onPause() {
        super.onPause();
         
        // on pause turn off the flash
        turnOffFlash();
    }
 
    @Override
    protected void onRestart() {
        super.onRestart();
    }
 
    @Override
    protected void onResume() {
        super.onResume();
    }
 
    @Override
    protected void onStart() {
        super.onStart();
         
        // on starting the app get the camera params
        getCamera();
    }
 
    @Override
    protected void onStop() {
        super.onStop();
         
        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
