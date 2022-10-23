package com.example.avia.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AviaPermissionDto {

    @NotNull
    private long aviaId;

    @NotNull
    @NotBlank
    private String permission;
}
