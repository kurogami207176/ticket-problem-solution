package com.alaindroid.sportsbet.config;

import com.alaindroid.sportsbet.pricing.model.Pricing;
import com.alaindroid.sportsbet.ticketing.model.CustomerCategories;
import com.alaindroid.sportsbet.ticketing.model.DiscountConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class ApplicationConfiguration {
    private Pricing pricing;
    private DiscountConfiguration discount;
    private CustomerCategories customer;

    @Bean
    public Pricing pricing() {
        return pricing;
    }

    @Bean
    public DiscountConfiguration discountConfiguration() {
        return discount;
    }

    @Bean
    public CustomerCategories customerCategories() {
        return customer;
    }
}
