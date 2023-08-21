package com.alaindroid.sportsbet.pricing.service;

import com.alaindroid.sportsbet.model.TicketType;
import com.alaindroid.sportsbet.pricing.data.PricingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

    @Autowired
    private PricingData pricingData;
    public BigDecimal getPrice(TicketType type) {
        return pricingData.getPrice(type);
    }
}
