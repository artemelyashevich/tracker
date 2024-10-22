package com.elyashevich.tracker.api.mapper;

import java.util.List;

public interface Mappable<E, D> {

    List<D> toDto(List<E> entities);

    D toDto(E entity);

    E toEntity(D dto);
}
