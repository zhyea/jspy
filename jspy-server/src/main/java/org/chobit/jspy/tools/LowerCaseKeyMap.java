package org.chobit.jspy.tools;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class LowerCaseKeyMap<V> implements Map<String, V>, Serializable, Cloneable {

    private final LinkedHashMap<String, V> targetMap;

    private final Locale locale;

    public LowerCaseKeyMap() {
        this(16, null);
    }

    public LowerCaseKeyMap(int initialCapacity) {
        this(initialCapacity, null);
    }

    public LowerCaseKeyMap(Locale locale) {
        this(16, locale);
    }

    public LowerCaseKeyMap(int initialCapacity, Locale locale) {
        this.targetMap = new LinkedHashMap<>(initialCapacity);
        this.locale = null == locale ? Locale.getDefault() : locale;
    }


    private LowerCaseKeyMap(LowerCaseKeyMap<V> other) {
        this.targetMap = (LinkedHashMap<String, V>) other.targetMap.clone();
        this.locale = other.locale;
    }

    @Override
    public int size() {
        return this.targetMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.targetMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return (key instanceof String && this.targetMap.containsKey(convertKey((String) key)));
    }

    @Override
    public boolean containsValue(Object value) {
        return this.targetMap.containsValue(value);
    }

    @Override
    @Nullable
    public V get(Object key) {
        if (key instanceof String) {
            return this.targetMap.get(convertKey((String) key));

        }
        return null;
    }

    @Override
    @Nullable
    public V getOrDefault(Object key, V defaultValue) {
        if (key instanceof String) {
            return this.targetMap.get(convertKey((String) key));
        }
        return defaultValue;
    }

    @Override
    @Nullable
    public V put(String key, @Nullable V value) {
        return this.targetMap.put(convertKey(key), value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        if (map.isEmpty()) {
            return;
        }
        map.forEach(this::put);
    }

    @Override
    @Nullable
    public V remove(Object key) {
        if (key instanceof String) {
            return this.targetMap.remove(convertKey((String) key));
        }
        return null;
    }

    @Override
    public void clear() {
        this.targetMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.targetMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.targetMap.values();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return this.targetMap.entrySet();
    }

    @Override
    public LowerCaseKeyMap<V> clone() {
        return new LowerCaseKeyMap<>(this);
    }

    @Override
    public boolean equals(Object obj) {
        return this.targetMap.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.targetMap.hashCode();
    }

    @Override
    public String toString() {
        return this.targetMap.toString();
    }

    public Locale getLocale() {
        return locale;
    }

    protected String convertKey(String key) {
        return key.toLowerCase(getLocale());
    }

    public String getString(String key) {
        if (get(key) instanceof String) {
            return (String) get(key);
        }
        return get(key).toString();
    }

    public Long getLong(String key) {
        if (get(key) instanceof Long) {
            return (Long) get(key);
        }
        throw new NumberFormatException();
    }


    public double getDouble(String key) {
        Object v = get(key);
        if (v instanceof Long) {
            return 1.0 * (Long) v;
        }
        if (v instanceof Double) {
            return (Double) v;
        }
        if (v instanceof BigDecimal) {
            return ((BigDecimal) v).doubleValue();
        }
        throw new NumberFormatException();
    }


    public long getTime(String key) {
        Object v = get(key);
        if (v instanceof Date) {
            Date d = (Date) v;
            return d.getTime();
        }
        throw new IllegalArgumentException();
    }

    public Integer getInt(String key) {
        if (get(key) instanceof Long) {
            return (Integer) get(key);
        }
        throw new NumberFormatException();
    }
}
