package cl.idesoft.idesoftschallenge.service;

import cl.idesoft.idesoftschallenge.model.Local;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static cl.idesoft.idesoftschallenge.IdesoftsChallengeApplication.locales;

@Service
@Slf4j
public class LocalServiceImpl implements LocalService{

    @Override
    public List<Local> getAllLocales() {
        log.info("we are searching all locales");
        return locales;
    }

    @Override
    public List<Local> getLocalesByCommune(String commune) {
        log.info("we are searching locals in {} commune",commune);
        return locales.stream()
                .filter(local -> local.getCommune().equalsIgnoreCase(commune))
                .collect(Collectors.toList());
    }
}
