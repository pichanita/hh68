package nso.hh2568.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.RelativeLayout;
import android.widget.TableRow;

public class RelativeWithBorder extends RelativeLayout {

	private float weight;
	private Paint BorderColor;	
	private int bgColor;
	private int border;
	
	public RelativeWithBorder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public RelativeWithBorder(Context context, int bgColor,int centerVertical, Paint paint, int color, float weight, int border) {
		/**
		 * @bgColor is backgroundColor
		 * @centerVertical is gravity
		 * @paint is Border color
		 * @color is drawColor
		 * @weight is weight of border
		 * @border is flag for set border 
		 * 1 = box
		 * 2 = only left and right
		 * 3 = only top and bottom
		 * 4 = only line on top
		 * 5 = only line on bottom
		 * 6 = only left
		 * 7 = only right
		 * 8 = 2+5
		 */
		super(context);
		// TODO Auto-generated constructor stub
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.setBackgroundColor(bgColor);		
		this.setGravity(centerVertical);
		this.setBorder(paint, color, weight, border);
	}

	public void setBorder(Paint BorderColor,int bgColor,float weight,int border){
		this.bgColor = bgColor;
		this.BorderColor = BorderColor;
		this.weight = weight;
		this.border = border;
	}

	@Override
	protected void onDraw(Canvas canvas){ 
		canvas.drawColor(bgColor);
		
		
		//public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)

		//canvas.drawText(" *)", 5, getMeasuredHeight() - 4, BorderColor);
		//top
		if(border == 1 || border == 3 || border == 4 || border == 8){
			canvas.drawLine(0, 0, getMeasuredWidth(), 0, BorderColor);
		}
		//left
		if(border == 1 || border == 2 || border == 6 || border == 8){
			canvas.drawLine(0, 0, 0, getMeasuredHeight(), BorderColor);
		}
		//right
		if(border == 1 || border == 2 || border == 7 ){
			canvas.drawLine(getMeasuredWidth(), 0, getMeasuredWidth(), getMeasuredHeight(), BorderColor);
		}
		//bottom
		if(border == 1 || border == 3 || border == 5){
			canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), BorderColor);
		}
		canvas.save();
		canvas.translate(weight, 10);
		super.onDraw(canvas);
		canvas.restore();
	}

}

