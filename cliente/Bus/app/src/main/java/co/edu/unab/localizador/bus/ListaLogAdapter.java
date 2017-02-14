package co.edu.unab.localizador.bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josue on 2/3/17.
 */

public class ListaLogAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    protected ArrayList<LogData> ly_logs;

    public ListaLogAdapter(Context _context, ArrayList<LogData> _lylogs){
        this.context = _context;
        this.ly_logs = _lylogs;
    }

    @Override
    public int getCount() {
        return ly_logs.size();
    }

    @Override
    public Object getItem(int i) {
        return ly_logs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemLogView = view;
        if (view == null) {
            inflater = LayoutInflater.from(context);
            itemLogView = inflater.inflate(R.layout.item_log, viewGroup, false);
        }
        TextView txtLog = (TextView) itemLogView.findViewById(R.id.itemLog);
        LogData item = ly_logs.get(i);
        txtLog.setText(item.getMensaje());

        return itemLogView;
    }
}
