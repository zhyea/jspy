package org.chobit.jspy.utils;

import org.chobit.jspy.exceptions.JSpyArgsException;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.service.entity.AbstractEntity;

import java.util.List;

import static org.chobit.jspy.core.utils.Strings.*;
import static org.chobit.jspy.utils.Reflections.nameOfFields;


public abstract class Args {

    public static void check(boolean result, String errMsg) {
        if (result) {
            throw new JSpyArgsException(errMsg);
        }
    }

    public static void checkNotNull(Object src, String errMsg) {
        check(null == src, errMsg);
    }

    public static void checkNull(Object src, String errMsg) {
        check(null != src, errMsg);
    }


    public static void checkNotBlank(String src, String errMsg) {
        check(isBlank(src), errMsg);
    }


    public static void checkPage(Page page, Class<? extends AbstractEntity> entityClass) {
        check(page.getLimit() < 0, "limit value in Page is lower than zero.");
        check(page.getOffset() < 0, "offset value in Page is lower than zero.");
        if (null == page.getOrder()) {
            page.setOrder(Page.Direct.desc);
        }
        if (isBlank(page.getSort())) {
            return;
        }

        String sortInHump = page.getSort().contains("_") ? lineToHump(page.getSort()) : page.getSort();

        List<String> fields = Reflections.nameOfFields(entityClass);

        check(!fields.contains(sortInHump), "sort in Page is illegal.");

        String sortInLine = humpToLine(page.getSort());

        page.setSort(sortInLine);
    }


    private Args() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
