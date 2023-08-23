package com.alaindroid.sportsbet.common.mapper;

import com.alaindroid.sportsbet.api.model.OrderRequest;
import com.alaindroid.sportsbet.api.model.OrderResponse;
import com.alaindroid.sportsbet.orders.model.OrderDelegateRequest;
import com.alaindroid.sportsbet.orders.model.OrderDelegateResponse;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    TicketingRequest map(OrderDelegateRequest request);

    OrderResponse map(OrderDelegateResponse response);

    OrderDelegateRequest map(OrderRequest request);
}
