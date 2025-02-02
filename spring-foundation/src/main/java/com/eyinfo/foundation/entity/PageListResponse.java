package com.eyinfo.foundation.entity;

public class PageListResponse<T> extends BaseResponse {
    //总记录数
    private long count;
    //列表数据
    private T data;
    //是否第一页数据
    private boolean isIsFirstPage;
    //是否包含下一页数据
    private boolean isHasNextPage;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isIsFirstPage() {
        return isIsFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        isIsFirstPage = isFirstPage;
    }

    public boolean isHasNextPage() {
        return isHasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        isHasNextPage = hasNextPage;
    }
}
