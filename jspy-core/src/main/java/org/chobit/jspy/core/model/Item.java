package org.chobit.jspy.core.model;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.chobit.jspy.core.utils.Strings.isBlank;

public class Item {

    private String title;

    private Map<String, Object> details = new LinkedHashMap<>(4);


    public Item() {
    }

    public Item(String title) {
        this.title = title;
    }


    public void add(String key, Object value) {
        if (null == value) {
            value = "-";
        } else if (value instanceof String && isBlank(value.toString())) {
            value = "-";
        }
        this.details.put(key, value);
    }


    public String getTitle() {
        return title;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
