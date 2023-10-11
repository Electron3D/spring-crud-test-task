package com.example.demo.data.mapper;

public abstract class AbstractMapper<E, D> {
    public abstract E dtoToEntity(D dto);
    public abstract D entityToDto(E entity);
}
