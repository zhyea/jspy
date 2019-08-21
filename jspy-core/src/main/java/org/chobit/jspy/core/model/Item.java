package org.chobit.jspy.core.model;

import java.util.HashMap;
import java.util.Map;

public class Item {

    private String title;

    private Map<String, String> details = new HashMap<>(4);

    
    public Item(String title) {
        this.title = title;
    }


    public void add(String key, String value) {
        this.details.put(key, value);
    }


    public String getTitle() {
        return title;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
