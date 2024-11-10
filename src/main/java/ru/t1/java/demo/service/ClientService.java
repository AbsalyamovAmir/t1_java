package ru.t1.java.demo.service;

import ru.t1.java.demo.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {
    List<Client> parseJson() throws IOException;

    Client findById(Long id);

    List<Client> findAll();

    void generateClients();
}
