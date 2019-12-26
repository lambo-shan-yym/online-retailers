package com.lambo.onlineretailers.page;

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
    private final static ThreadLocal<SystemRequest> systemRequesthreadLocal = new ThreadLocal<SystemRequest>();
    private final static ThreadLocal<Pageable> pageableThreadLocal = new ThreadLocal<Pageable>();

    public static void initRequestHolder(HttpServletRequest request) {
        int offset = 0;
        int size = 10;
        String offsetStr = request.getParameter("pageOffset");
        String sizeStr = request.getParameter("pageSize");
        if (null != offsetStr && !"".equals(offsetStr)) {
            offset = Integer.valueOf(offsetStr);
        }
        if (null != sizeStr && !"".equals(sizeStr)) {
            size = Integer.valueOf(sizeStr);
        }
        String orderStr = request.getParameter("order");
        String sortStr = request.getParameter("sort");
        SystemRequest systemRequest = new SystemRequest();
        systemRequest.setOrder(orderStr);
        systemRequest.setPageOffset(offset);
        systemRequest.setPageSize(size);
        systemRequest.setRequest(request);
        systemRequest.setSort(sortStr);
        Pageable pageable = null;
        if (StringUtils.isNotBlank(orderStr)&&StringUtils.isNotBlank(sortStr)){
            pageable = PageRequest.of(systemRequest.getPageOffset(), systemRequest.getPageSize(),
                    Sort.Direction.ASC, "id");
        }else {
            pageable = PageRequest.of(systemRequest.getPageOffset(), systemRequest.getPageSize());
        }
        pageableThreadLocal.set(pageable);
        systemRequesthreadLocal.set(systemRequest);

    }

    public static Pageable getPageable() {
        return pageableThreadLocal.get();
    }

    public static SystemRequest getSystemRequest() {
        return systemRequesthreadLocal.get();
    }

    public static void remove() {
        systemRequesthreadLocal.remove();
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