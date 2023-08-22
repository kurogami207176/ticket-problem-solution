package com.alaindroid.sportsbet.common.mapper;

import com.alaindroid.sportsbet.orders.model.OrderRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    TicketingRequest map(OrderRequest request);
}
