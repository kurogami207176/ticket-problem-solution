package com.alaindroid.sportsbet.transaction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionIdServiceTest {

    private TransactionIdService subject = new TransactionIdService();
    @Test
    void getResponseTransactionId() {
        assertThat(subject.getResponseTransactionId(23))
                .isEqualTo(1);
    }
}