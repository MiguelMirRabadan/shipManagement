package com.empire.shipmanagement.business.core.application.port.in;


import com.empire.shipmanagement.business.core.application.dto.SearchResultDto;
import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.domain.port.io.ShipDomainManagementPort;

public interface ShipBusinessManagementPort extends ShipDomainManagementPort {

    SearchResultDto getShipSearchResult(ShipFilter shipFilter);
}
