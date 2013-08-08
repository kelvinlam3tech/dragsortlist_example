package com.example.dragsortlistview;

import java.util.ArrayList;
import java.util.Arrays;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {
	
	DragSortListView listView;
	ArrayAdapter<String> adapter;

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
	{
	    @Override
	    public void drop(int from, int to)
	    {
	        if (from != to)
	        {
	            String item = adapter.getItem(from);
	            adapter.remove(item);
	            adapter.insert(item, to);
	        }
	    }
	};

	private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener()
	{
	    @Override
	    public void remove(int which)
	    {
	        adapter.remove(adapter.getItem(which));
	    }
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (DragSortListView) findViewById(R.id.dragSortListView1);
        String[] names = getResources().getStringArray(R.array.list);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(names));
        adapter = new ArrayAdapter<String>(this,
                R.layout.item_layout, R.id.textView1, list);
        listView.setAdapter(adapter);
        listView.setDropListener(onDrop);
        listView.setRemoveListener(onRemove);

        DragSortController controller = new DragSortController(listView);
        controller.setDragHandleId(R.id.rrl);
        controller.setDragInitMode(DragSortController.ON_LONG_PRESS);
                //controller.setClickRemoveId(R.id.);
        controller.setRemoveEnabled(true);
        
        controller.setSortEnabled(true);
        //controller.setDragInitMode(1);
                //controller.setRemoveMode(removeMode);

        listView.setFloatViewManager(controller);
        listView.setOnTouchListener(controller);
        listView.setDragEnabled(true);
    }

    
}
