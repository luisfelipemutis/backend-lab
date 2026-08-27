package com.felipe.backendlab.playground.snackMachineFile.service;

import com.felipe.backendlab.playground.snackMachineFile.domain.Snack;

import java.util.List;

public interface IServiceSnacks {

    void addSnacks(Snack s);

    void showSnacks();

    List<Snack> getSnacks();

}
