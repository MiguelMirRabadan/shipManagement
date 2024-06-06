package com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper;

import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

public abstract class DomainMapper<S, T> {

    private final ModelMapper modelMapper;

    public DomainMapper() {
        this.modelMapper = new ModelMapper();
    }
    public S toLocalModel(T target) {
        return modelMapper.map(target, getSourceClass());
    }

    public T toExternalModel(S source) {
        return modelMapper.map(source, getTargetClass());
    }

    // Método abstracto para obtener el tipo de clase origen
    @SuppressWarnings("unchecked")
    protected Class<S> getSourceClass() {
        return (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // Método para obtener el tipo de clase destino
    @SuppressWarnings("unchecked")
    protected Class<T> getTargetClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
