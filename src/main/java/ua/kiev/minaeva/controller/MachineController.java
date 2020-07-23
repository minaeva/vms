package ua.kiev.minaeva.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ua.kiev.minaeva.dto.MachineDto;
import ua.kiev.minaeva.exception.VMSValidationException;
import ua.kiev.minaeva.service.MachineService;

import java.util.List;

@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor
@Log
@CrossOrigin
public class MachineController {

    private final MachineService machineService;

    @GetMapping
    public List<MachineDto> getAllMachines() {
        log.info("handling GET ALL MACHINES request");
        return machineService.getAll();
    }

    @PostMapping
    public MachineDto createMachine(@RequestBody MachineDto machineDto) throws VMSValidationException {
        log.info("handling CREATE MACHINE request: " + machineDto);
        return machineService.addMachine(machineDto);
    }

}

