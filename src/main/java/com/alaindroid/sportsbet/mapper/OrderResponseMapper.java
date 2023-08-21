package com.alaindroid.sportsbet.mapper;

import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import com.alaindroid.sportsbet.transport.model.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    OrderResponse map(TicketingResponse request);
}
