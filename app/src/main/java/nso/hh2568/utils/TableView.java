package nso.hh2568.utils;


import android.content.Context;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;

public class TableView     {

	private Context context;
	private LinearLayout table;
	private TableLayout header;
	private TableLayout bottom;
	private TableLayout bottom2;
	private LinearLayout body;
	private TableLayout tbody;
	private HorizontalScrollView hScrollMain;
	private LinearLayout main;
	private ScrollView sc;
	private int height;
		
	public TableView(Context context,LinearLayout main)
	{
		 
		this.context = context;
		this.main = main;		
		hScrollMain = new HorizontalScrollView(context);
		hScrollMain.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 580));
		//hScrollMain.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		hScrollMain.setMinimumHeight(650);
		height = 580;
		Default();
	}
	
	public TableView(Context context,LinearLayout main,int h,int minH) {
	 
		this.context = context;
		this.main = main;
		hScrollMain = new HorizontalScrollView(context);
		hScrollMain.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, h));
		hScrollMain.setMinimumHeight(minH);
		height = minH;
		Default();
	}
	
	public int getScrollHeight(){
		return height;
	}
	
	public void setVisible(int visibility){
		hScrollMain.setVisibility(visibility);
	}

	public void setHeight(int height){
		if(height == 0)
		{
			
		}else{
			sc.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, height));
		}		
	}
	
	public void setScrollHeight(int height){
		if(height == 0)
		{
			hScrollMain.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			hScrollMain.setMinimumHeight(0);
		}else{
			hScrollMain.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, height));
		}	
		this.height = height;		
		//table.setWeightSum(1);
	}
	private void Default(){
		table = new LinearLayout(context);
		//table.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, 550));
		table.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		table.setOrientation(LinearLayout.VERTICAL);
		hScrollMain.addView(table);
		
		header = new TableLayout(context);
		header.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		table.addView(header);
		
		sc = new ScrollView(context);
		sc.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	
		table.addView(sc);
		
		bottom = new TableLayout(context);
		bottom.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		table.addView(bottom);
		
		body = new LinearLayout(context);
		body.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		body.setGravity(new Gravity().CENTER_HORIZONTAL);
		body.setOrientation(LinearLayout.VERTICAL);
		sc.addView(body);
		
		HorizontalScrollView hScroll = new HorizontalScrollView(context);
		//hScroll.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, 580));
		hScroll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 0));
		//hScroll.setMinimumHeight(650);
		//body.addView(hScroll);
		
		tbody = new TableLayout(context);
		tbody.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		//hScroll.addView(tbody);
		body.addView(tbody);
		
		bottom2 = new TableLayout(context);
		bottom2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		body.addView(bottom2);
		
		/*ScrollView Scroll = new ScrollView(context);
		Scroll.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		Scroll.addView(hScrollMain);*/
		main.addView(hScrollMain);
		//bottom2 = new TableLayout(context);
		//bottom2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		//main.addView(bottom2);
	}

	public void addHeader(TableRow row){		
		header.addView(row);
	}
	
	public void addRow(TableRow row){
		tbody.addView(row);
	}
	
	public void addBottom(TableRow row){
		bottom.addView(row);
	}
	
	public void addBottom2(TableRow row){
		bottom2.addView(row);
	}
	
	public void delBottom2(){
		bottom2.removeAllViews();
	}
	
	public void delAllBottomRow(){
		bottom.removeAllViews();
	}
	public int getBodyCount()
	{
		return tbody.getChildCount();
	}
	public TableLayout getTableBody()
	{
		return tbody;
	}
	public void delRow(TableRow row){
		tbody.removeView(row);
	}
	public void delAllBodyRow(){
		tbody.removeAllViews();
		bottom.removeAllViews();
	}
	public void delAllBodyRowWithOutBottom(){
		tbody.removeAllViews();
	}
	public void delAllHeader(){
		header.removeAllViews();
	}
}

