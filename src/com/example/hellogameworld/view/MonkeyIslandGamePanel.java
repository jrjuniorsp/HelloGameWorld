package com.example.hellogameworld.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.hellogameworld.R;
import com.example.hellogameworld.model.ElaineAnimated;
import com.example.hellogameworld.thread.MonkeyIslandThread;

public class MonkeyIslandGamePanel extends SurfaceView implements SurfaceHolder.Callback {
	
	private ElaineAnimated elaine;
	private MonkeyIslandThread thread;

	public MonkeyIslandGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		//Cria o objeto animado
		elaine = new ElaineAnimated(BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine), 10, 10);
		//Cria a thread
		thread = new MonkeyIslandThread(getHolder(), this);
	}
	
	public void update() {
		elaine.update(System.currentTimeMillis());
	}
	
	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		elaine.draw(canvas);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Toast.makeText(getContext(), "Good bye", Toast.LENGTH_SHORT).show();
			thread.setRunning(false);
			((Activity) getContext()).finish();			
		}
		return super.onTouchEvent(event);
	}
	
	

}
