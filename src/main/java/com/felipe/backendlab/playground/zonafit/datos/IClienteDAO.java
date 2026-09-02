package com.felipe.backendlab.playground.zonafit.datos;

import java.util.List;

import com.felipe.backendlab.playground.zonafit.dominio.Cliente;

public interface IClienteDAO {

    List<Cliente> getClientes();

    Cliente getClienteById(int id);

    boolean addCliente(Cliente cliente);

    boolean updateCliente(Cliente cliente);

    boolean deleteCliente(int id);

}
