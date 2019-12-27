package com.lambo.onlineretailers.page;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @ClassName: SystemRequest
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/26 20:51
 * @Version: 1.0
 */
public class SystemRequest implements Serializable {
    private static final long serialVersionUID = -4168104962029946743L;
    public static final int DEFAULT_PAGE_SIZE = 10;
    private HttpServletRequest request;
    private int pageSize;
    private int pageOffset;
    private String sort;
    private String order[];

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getPageSize() {
        return (0 >= pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageOffset() {
        return (pageOffset <= 0) ? 0 : pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SystemRequest [request=" + request + ", pageSize=" + pageSize + ", pageOffset=" + pageOffset + ", sort="
                + sort + ", order=" + order + "]";
    }
}