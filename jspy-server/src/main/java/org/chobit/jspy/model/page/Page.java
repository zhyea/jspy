package org.chobit.jspy.model.page;

import static org.chobit.jspy.model.page.Page.Direct.desc;


/**
 * 分页查询项
 */
public class Page {

    private String search;

    private int offset;

    private int limit;

    private String sort;

    private Direct order = desc;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Direct getOrder() {
        return order;
    }

    public void setOrder(Direct order) {
        this.order = order;
    }

    public enum Direct {

        asc,

        desc,
        ;
    }
}
