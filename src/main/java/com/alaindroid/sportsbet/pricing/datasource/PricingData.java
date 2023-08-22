package com.alaindroid.sportsbet.pricing.datasource;

import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.pricing.model.Pricing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class PricingData {
    private Pricing pricing;
    public BigDecimal getPrice(TicketType type) {
        return pricing.matrix().get(type);
    }
}
