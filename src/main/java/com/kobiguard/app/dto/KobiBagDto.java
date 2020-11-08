package com.kobiguard.app.dto;

import java.util.List;

public class KobiBagDto {

    private String id;

    private List<SelectedProductDto> add;

    private List<SelectedProductDto> remove;

    public KobiBagDto() {
    }

    public List<SelectedProductDto> getAdd() {
        return add;
    }

    public void setAdd(List<SelectedProductDto> add) {
        this.add = add;
    }

    public List<SelectedProductDto> getRemove() {
        return remove;
    }

    public void setRemove(List<SelectedProductDto> remove) {
        this.remove = remove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
