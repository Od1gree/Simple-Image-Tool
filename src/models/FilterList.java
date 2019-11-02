package models;

import javax.sound.midi.Track;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class FilterList {
    private TreeMap<Integer, Filter> filters;
    private int idIndex;

    public FilterList(){
        filters = new TreeMap<>();
    }

    public void addFilter(Filter filter){
        filter.setId(idIndex);
        filters.put(idIndex++,filter);
    }

    public void delFilter(int id){
        filters.remove(id);
    }

    public Filter getFilter(int id){
        return filters.get(id);
    }

    public void saveFilters(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(filters);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
