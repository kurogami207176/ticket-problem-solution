package com.alaindroid.sportsbet.pricing.data;

import com.alaindroid.sportsbet.model.TicketType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class PricingData {
    private static final Map<TicketType, BigDecimal> pricesDb = new HashMap<>();

    static {
        pricesDb.put(TicketType.ADULT, BigDecimal.valueOf(25.0));
        pricesDb.put(TicketType.SENIOR, BigDecimal.valueOf(25.0 * 0.7));
        pricesDb.put(TicketType.TEEN, BigDecimal.valueOf(12.0));
        pricesDb.put(TicketType.CHILDREN, BigDecimal.valueOf(5.0));
    }

    public BigDecimal getPrice(TicketType type) {
        return pricesDb.get(type);
    }
}
