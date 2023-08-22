package com.alaindroid.sportsbet.pricing.service;

import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.pricing.datasource.PricingData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PricingService {

    private PricingData pricingData;

    public BigDecimal getPrice(TicketType type) {
        return pricingData.getPrice(type);
    }
}
