package com.example.junglebook.data.common;

public interface EntityMapper <DTO, Entity> {
    Entity toEntity(DTO dto);

}
