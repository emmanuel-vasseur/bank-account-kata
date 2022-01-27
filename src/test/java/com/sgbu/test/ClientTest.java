package com.sgbu.test;

import com.sgbu.Client;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
class ClientTest {

    @ParameterizedTest
    @ValueSource(strings = {"client1", "client2"})
    @NullAndEmptySource
    void client_should_encapsulate_client_id(String clientId){
        Client client = new Client(clientId);
        assertThat(client.getId()).isEqualTo(clientId);
    }

}
