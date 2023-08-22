package com.alaindroid.sportsbet.pricing.model;

import com.alaindroid.sportsbet.common.model.TicketType;

import java.math.BigDecimal;
import java.util.Map;


public record Pricing (Map<TicketType, BigDecimal> matrix) { }
