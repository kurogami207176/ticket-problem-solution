package com.alaindroid.sportsbet.mapper;

import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.transport.model.OrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    TicketingRequest map(OrderRequest request);
}
