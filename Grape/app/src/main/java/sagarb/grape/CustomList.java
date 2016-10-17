package sagarb.grape;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Dell on 11-10-2016.
 */
public class CustomList extends ArrayAdapter<String> {

    private String[] names;
    private String[] emails;
    private String[] colleges;
    private String[] profs;
    private Activity context;
    public CustomList(Activity context,String names[],String emails[],String colleges[],String profs[]) {
        super(context, R.layout.list_view_layout,names);
        this.names=names;
        this.emails=emails;
        this.colleges=colleges;
        this.profs=profs;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_layout, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        TextView email = (TextView) convertView.findViewById(R.id.Email);
        TextView college = (TextView) convertView.findViewById(R.id.College);
        TextView prof = (TextView) convertView.findViewById(R.id.Prof);

        name.setText(names[position]);
        email.setText(emails[position]);
        college.setText(colleges[position]);
        prof.setText(profs[position]);

        return convertView;
    }
}

