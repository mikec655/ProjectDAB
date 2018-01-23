package logic;

import java.util.ArrayList;
import view.AbstractView;

public abstract class AbstractModel {
private ArrayList<AbstractView> views;
	
	//maakt een arraylist aan waar alle views in worden opgeslagen.
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	//voegt een nieuwe view toe aan de arraylist.
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	//update de views in realtime.
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
}
