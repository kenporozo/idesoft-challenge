package cl.idesoft.idesoftschallenge.service;

import cl.idesoft.idesoftschallenge.model.Local;

import java.util.List;

public interface LocalService {

    List<Local> getAllLocales();

    List<Local> getLocalesByCommune(String commune);
}
