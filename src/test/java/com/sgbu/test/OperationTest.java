package com.sgbu.test;

import com.sgbu.account.Operation;
import com.sgbu.account.OperationType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperationTest {

    @Test
    void operation_should_encapsulate_attributes() {
        Instant operationDate = Instant.now();
        Operation operation = new Operation(OperationType.DEPOSIT, BigDecimal.ONE, operationDate, BigDecimal.TEN);
        assertThat(operation.getAmount()).isEqualTo(BigDecimal.ONE);
        assertThat(operation.getOperationType()).isEqualTo(OperationType.DEPOSIT);
        assertThat(operation.getDate()).isEqualTo(operationDate);
        assertThat(operation.getBalance()).isEqualTo(BigDecimal.TEN);
    }

}
