package com.empire.shipmanagement.infraestructure.adapter.out.persitence.adapter;

import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityFilterFactory;
import org.springframework.data.domain.*;

import java.util.Optional;

import static com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity.*;



public class ShipExampleFactory {
    private static ExampleMatcher buildExampleMatcher() {
        return ExampleMatcher.matchingAll()
                .withMatcher(ID_COLUMN_PATH, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher(DESCRIPTION_COLUMN_PATH, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher(NAME_COLUMN_PATH, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher(TYPE_COLUMN_PATH, ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher(STATUS_COLUMN_PATH, ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnoreNullValues();
    }

    public static Example<ShipEntity> buildExample(ShipFilter shipFilter) {
        return Example.of(ShipEntityFilterFactory.filterToEntity(shipFilter), buildExampleMatcher());
    }

    public static Pageable buildPageable(ShipFilter shipFilter) {
        return PageRequest.of(shipFilter.getPageIndex(), shipFilter.getPageSize(), buildSort(shipFilter));
    }

    public static Sort buildSort(ShipFilter shipFilter) {
        return Optional.ofNullable(shipFilter.getSortField())
                .map(sortField -> Sort.by(shipFilter.getSortDirection(), sortField))
                .orElseGet(Sort::unsorted);
    }
}
