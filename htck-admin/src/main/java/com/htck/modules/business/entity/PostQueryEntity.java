package com.htck.modules.business.entity;

/**
 * 职位查询包装类
 */
public class PostQueryEntity {
    // 职位名称
    private String postName;
    // 页码，默认第一页
    private int page = 1;
    // 每页行数，默认10
    private int rows = 10;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
