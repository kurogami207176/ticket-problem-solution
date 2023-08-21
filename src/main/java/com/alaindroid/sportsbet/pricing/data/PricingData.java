package com.alaindroid.sportsbet.pricing.data;

import com.alaindroid.sportsbet.model.TicketType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class PricingData {
    private static Map<TicketType, BigDecimal> prices = new HashMap<>();
    static {
        prices.put(TicketType.ADULT, BigDecimal.valueOf(25.0));
        prices.put(TicketType.SENIOR, BigDecimal.valueOf(25.0 * 0.7));
        prices.put(TicketType.TEEN, BigDecimal.valueOf(12.0));
        prices.put(TicketType.CHILDREN, BigDecimal.valueOf(5.0));
    }

    public BigDecimal getPrice(TicketType type) {
        return prices.get(type);
    }
}
