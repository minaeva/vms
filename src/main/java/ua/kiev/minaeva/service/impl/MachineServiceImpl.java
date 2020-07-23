package ua.kiev.minaeva.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.kiev.minaeva.dto.MachineDto;
import ua.kiev.minaeva.entity.Machine;
import ua.kiev.minaeva.exception.VMSValidationException;
import ua.kiev.minaeva.mapper.MachineMapper;
import ua.kiev.minaeva.repository.MachineRepository;
import ua.kiev.minaeva.service.MachineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private MachineMapper mapper = Mappers.getMapper(MachineMapper.class);

    public MachineDto addMachine(MachineDto machineDto) throws VMSValidationException {
        validateMachine(machineDto);

        Machine newMachine = mapper.dtoToMachine(machineDto);

        return mapper.machineToDto(machineRepository.save(newMachine));
    }

    public List<MachineDto> getAll() {
        List<Machine> machines = machineRepository.findAll();

        return machines.stream()
                .map(drink -> mapper.machineToDto(drink))
                .collect(Collectors.toList());
    }

    private void validateMachine(MachineDto machineDto) throws VMSValidationException {
        if (StringUtils.isEmpty(machineDto.getAddress())) {
            throw new VMSValidationException("Address cannot be empty");
        }
    }


}
