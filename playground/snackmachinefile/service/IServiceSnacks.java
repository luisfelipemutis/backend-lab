package com.felipe.backendlab.playground.snackmachinefile.service;

import java.util.List;

import com.felipe.backendlab.playground.snackmachinefile.domain.Snack;

public interface IServiceSnacks {

    void addSnacks(Snack s);

    void showSnacks();

    List<Snack> getSnacks();

}
