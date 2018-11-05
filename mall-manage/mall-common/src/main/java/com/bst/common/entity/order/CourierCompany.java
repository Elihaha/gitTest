package com.bst.common.entity.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class CourierCompany {
    private Long id;

    private String name;

    private String code;

    private LocalDateTime createDatetime;

    private LocalDateTime updateDatetime;

    private String platform;

    public CourierCompany() {
    }

    public CourierCompany(Long id, String name, String code, LocalDateTime createDatetime, LocalDateTime updateDatetime, String platform) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
        this.platform = platform;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }

    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }
}