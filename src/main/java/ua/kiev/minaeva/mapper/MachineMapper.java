package ua.kiev.minaeva.mapper;

import org.mapstruct.Mapper;
import ua.kiev.minaeva.dto.MachineDto;
import ua.kiev.minaeva.entity.Machine;

@Mapper
public interface MachineMapper {

    Machine dtoToMachine(MachineDto machineDto);

    MachineDto machineToDto(Machine machine);
}
