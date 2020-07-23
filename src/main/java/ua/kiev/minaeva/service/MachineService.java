package ua.kiev.minaeva.service;

import ua.kiev.minaeva.dto.MachineDto;
import ua.kiev.minaeva.exception.VMSValidationException;

import java.util.List;

public interface MachineService {

    List<MachineDto> getAll();

    MachineDto addMachine(MachineDto machineDto) throws VMSValidationException;
}


