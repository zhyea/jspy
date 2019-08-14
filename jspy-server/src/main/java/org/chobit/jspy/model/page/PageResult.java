package org.chobit.jspy.model.page;

import java.util.List;

/**
 * 分页结果
 */
public class PageResult<T> {

    private long total;

    private List<T> rows;


    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }
}
