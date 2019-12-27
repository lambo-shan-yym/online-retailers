package com.lambo.onlineretailers.page;

import com.lambo.onlineretailers.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: SystemRequestHolder
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 20:51
 * @Version: 1.0
 */

public class SystemRequestHolder {
    private final static ThreadLocal<SystemRequest> systemRequestThreadLocal = new ThreadLocal<>();
    private final static ThreadLocal<Pageable> pageableThreadLocal = new ThreadLocal<>();
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    public static final String SORT_REGEX = String.format("%s|%s", DESC, ASC);

    public static void initRequestHolder(HttpServletRequest request, String... fieldName) {
        int offset = 0;
        int size = 10;
        String offsetStr = request.getParameter("pageOffset");
        String sizeStr = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(offsetStr)) {
            offset = Integer.valueOf(offsetStr);
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            size = Integer.valueOf(sizeStr);
        }
        //String orderStr = request.getParameter("order");
        String sortStr = request.getParameter("sort");
        Sort.Direction direction = null;
        if (StringUtils.isNotBlank(sortStr)) {
            AssertUtil.isMatches(sortStr, SORT_REGEX, "sort");
            if (ASC.equalsIgnoreCase(sortStr)) {
                direction = Sort.Direction.ASC;
            } else {
                direction = Sort.Direction.DESC;
            }
        }
        SystemRequest systemRequest = new SystemRequest();
        systemRequest.setOrder(fieldName);
        systemRequest.setPageOffset(offset);
        systemRequest.setPageSize(size);
        systemRequest.setRequest(request);
        systemRequest.setSort(sortStr);
        Pageable pageable = null;
        if (direction != null) {
            if (fieldName != null && fieldName.length > 0) {
                pageable = PageRequest.of(systemRequest.getPageOffset(), systemRequest.getPageSize(),
                        direction, fieldName);
            } else {
                pageable = PageRequest.of(systemRequest.getPageOffset(), systemRequest.getPageSize(),
                        direction, "id");
            }
        } else {
            pageable = PageRequest.of(systemRequest.getPageOffset(), systemRequest.getPageSize());
        }
        pageableThreadLocal.set(pageable);
        systemRequestThreadLocal.set(systemRequest);

    }

    public static Pageable getPageable() {
        return pageableThreadLocal.get();
    }

    public static SystemRequest getSystemRequest() {
        return systemRequestThreadLocal.get();
    }

    public static void remove() {
        systemRequestThreadLocal.remove();
    }

    public static Integer getRequestPageOffset() {
        Integer pageOffset = SystemRequestHolder.getSystemRequest().getPageOffset();
        if (pageOffset == null || pageOffset < 1) {
            pageOffset = 1;
        }
        return pageOffset - 1;
    }

    public static Integer getRequestPageSize() {
        Integer pageSize = SystemRequestHolder.getSystemRequest().getPageSize();
        if (pageSize == null || pageSize < 0) {
            pageSize = SystemRequest.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }
}