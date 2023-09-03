package com.example.demo.data.mapper;

public interface AbstractMapper<E, D> {
    E dtoToEntity(D dto);
    D entityToDto(E entity);
}
