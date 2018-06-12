package study.battery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

 public class Battery extends View {
        Paint paint;
        RectF rect;
        RectF rectmini;
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        public Battery(Context context) {
            super(context);
            final String TAG = "draw";
            Log.v(TAG, "Draw: "+width+","+height);
            float batleft = (float) (width*0.25);
            float batrigt = (float) (width*0.75);
            float battop = (float) (height*0.05);
            float batbot = (float) (height*0.45);
            float batleftmini = (float) (width*0.4);
            float batrightmini = (float) (width*0.6);
            float battopmini = (float) (height*0.025);
            float batbotmini = (float) (height*0.09);
            paint = new Paint();
            rect = new RectF(batleft,battop,batrigt,batbot);
            rectmini = new RectF(batleftmini,battopmini,batrightmini,batbotmini);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);

            canvas.drawRoundRect(rect, 20, 20, paint);
            canvas.drawRoundRect(rectmini, 20, 20, paint);
            // canvas.drawRoundRect();
        }
    }
