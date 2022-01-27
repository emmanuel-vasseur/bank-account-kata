package com.sgbu.test;

import com.sgbu.account.Operation;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperationTest {

    @Test
    void operation_should_encapsulate_attributes() {
        Operation operation = new Operation(BigDecimal.ONE);
        assertThat(operation.getAmount()).isEqualTo(BigDecimal.ONE);
    }
}
