package cl.idesoft.idesoftschallenge.controller;

import cl.idesoft.idesoftschallenge.model.Local;
import cl.idesoft.idesoftschallenge.service.LocalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locales")
@RequiredArgsConstructor
public class LocalController {

    private final LocalServiceImpl localService;

    @GetMapping()
    public ResponseEntity<List<Local>> getLocales(){
        return ResponseEntity.ok(localService.getAllLocales());
    }

    @GetMapping("/{commune}")
    public ResponseEntity<List<Local>> getLocalesByFilter(@PathVariable(name = "commune") String commune){
        return ResponseEntity.ok(localService.getLocalesByCommune(commune));
    }
}
