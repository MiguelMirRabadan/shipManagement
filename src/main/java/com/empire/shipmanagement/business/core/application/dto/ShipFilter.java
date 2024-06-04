package com.empire.shipmanagement.business.core.application.dto;

import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ShipFilter {

    private Long id;

    private String name;

    private ShipType type;

    private ShipStatus status;

    private String description;

    private Set<Long> films;

    private Integer pageIndex;

    private Integer pageSize;

    private String sortField;

    private Sort.Direction sortDirection;

    public static class ShipFilterBuilder {

        public static final String PAGE_SIZE_IS_REQUIRED = "pageSize is required";
        public static final String PAGE_INDEX_IS_REQUIRED = "pageIndex is required";
        public static final String SORT_DIRECTION_IS_REQUIRED_IF_SORT_FIELD_IS_PROVIDED = "sortDirection is required if sortField is provided";

        public ShipFilterBuilder id(Optional<Long> id) {
            this.id = id.orElse(null);
            return this;
        }

        public ShipFilterBuilder name(Optional<String> name) {
            this.name = name.orElse(null);
            return this;
        }

        public ShipFilterBuilder type(Optional<ShipType> type) {
            this.type = type.orElse(null);
            return this;
        }

        public ShipFilterBuilder status(Optional<ShipStatus> status) {
            this.status = status.orElse(null);
            return this;
        }
        public ShipFilterBuilder description(Optional<String> description) {
            this.description = description.orElse(null);
            return this;
        }

        public ShipFilterBuilder films(Optional<Set<Long>> films) {
            this.films = films.orElse(null);
            return this;
        }
        public ShipFilterBuilder pageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
            return this;
        }

        public ShipFilterBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ShipFilterBuilder sortField(Optional<String> sortField) {
            this.sortField = sortField.orElse(null);
            return this;
        }

        public ShipFilterBuilder sortDirection(Optional<Sort.Direction> sortDirection) {
            this.sortDirection = sortDirection.orElse(null);
            return this;
        }

        public ShipFilter build() {
            checkMandatoryFields();
            return new ShipFilter(id, name, type, status, description,films, pageIndex, pageSize, sortField, sortDirection);
        }

        private void checkMandatoryFields() {
            Optional.ofNullable(this.sortField).ifPresent(field ->
                    Optional.ofNullable(this.sortDirection).orElseThrow(() ->
                            new RuntimeException(SORT_DIRECTION_IS_REQUIRED_IF_SORT_FIELD_IS_PROVIDED)));
            Optional.ofNullable(this.pageIndex).orElseThrow(() -> new RuntimeException(PAGE_INDEX_IS_REQUIRED));
            Optional.ofNullable(this.pageSize).orElseThrow(() -> new RuntimeException(PAGE_SIZE_IS_REQUIRED));
        }

    }
}
