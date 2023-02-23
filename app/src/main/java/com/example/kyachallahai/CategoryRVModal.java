package com.example.kyachallahai;

public class CategoryRVModal {
    private String category;
    private  String categoryImageUrl;

    public CategoryRVModal(String category,String categoryImageUrl) {
        this.category = category;
        this.categoryImageUrl=categoryImageUrl;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
