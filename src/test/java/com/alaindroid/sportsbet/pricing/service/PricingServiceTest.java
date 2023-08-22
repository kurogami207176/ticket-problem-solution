package com.alaindroid.sportsbet.pricing.service;

import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.pricing.datasource.PricingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricingServiceTest {
    private PricingService subject;

    BigDecimal ADULT_PRICE = BigDecimal.ONE;
    BigDecimal SENIOR_PRICE = BigDecimal.TEN;

    @BeforeEach
    void init(@Mock PricingData pricingData) {
        subject = new PricingService(pricingData);
        when(pricingData.getPrice(eq(TicketType.ADULT)))
                .thenReturn(ADULT_PRICE);
        when(pricingData.getPrice(eq(TicketType.SENIOR)))
                .thenReturn(SENIOR_PRICE);
    }

    @Test
    void testPricingPullsFromDb(@Mock PricingData pricingData) {
        assertThat(subject.getPrice(TicketType.ADULT))
                .isEqualByComparingTo(ADULT_PRICE);
        assertThat(subject.getPrice(TicketType.SENIOR))
                .isEqualByComparingTo(SENIOR_PRICE);
    }

}