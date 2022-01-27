package com.sgbu;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Client client) {
        super("Client not found: " + client.getId());
    }
}
