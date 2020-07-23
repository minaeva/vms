package ua.kiev.minaeva.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.kiev.minaeva.dto.DrinkDto;
import ua.kiev.minaeva.entity.Drink;
import ua.kiev.minaeva.entity.Machine;
import ua.kiev.minaeva.exception.VMSNotFoundException;
import ua.kiev.minaeva.exception.VMSValidationException;
import ua.kiev.minaeva.mapper.DrinkMapper;
import ua.kiev.minaeva.repository.DrinkRepository;
import ua.kiev.minaeva.repository.MachineRepository;
import ua.kiev.minaeva.service.DrinkService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    public static final String NO_DRINK_WITH_ID = "No drink with id ";
    public static final String COULD_BE_FOUND = " could be found";
    private final DrinkRepository drinkRepository;
    private final MachineRepository machineRepository;

    private DrinkMapper mapper = Mappers.getMapper(DrinkMapper.class);

    public DrinkDto addDrink(DrinkDto drinkDto) throws VMSValidationException, VMSNotFoundException {
        validateDrink(drinkDto);

        Drink newDrink = mapper.dtoToDrink(drinkDto);

        Machine machine = getMachineById(drinkDto.getMachineId());
        newDrink.setMachine(machine);

        return mapper.drinkToDto(drinkRepository.save(newDrink));
    }

    public DrinkDto updateDrink(DrinkDto drinkDto) throws VMSValidationException, VMSNotFoundException {
        validateDrink(drinkDto);

        drinkRepository.findById(drinkDto.getId())
                .orElseThrow(() -> new VMSNotFoundException(NO_DRINK_WITH_ID + drinkDto.getId() + COULD_BE_FOUND));

        Drink drinkToUpdate = mapper.dtoToDrink(drinkDto);

        Machine newMachine = getMachineById(drinkDto.getMachineId());
        drinkToUpdate.setMachine(newMachine);

        return mapper.drinkToDto(drinkRepository.save(drinkToUpdate));
    }

    public void deleteDrink(DrinkDto drinkDto) throws VMSNotFoundException {
        drinkRepository.findById(drinkDto.getId())
                .orElseThrow(() -> new VMSNotFoundException(NO_DRINK_WITH_ID + drinkDto.getId() + COULD_BE_FOUND));

        drinkRepository.delete(mapper.dtoToDrink(drinkDto));
    }

    public List<DrinkDto> getAll() {
        List<Drink> drinks = drinkRepository.findAll();

        return drinks.stream()
                .map(drink -> mapper.drinkToDto(drink))
                .collect(Collectors.toList());
    }

    public DrinkDto getById(Long id) throws VMSNotFoundException {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new VMSNotFoundException(NO_DRINK_WITH_ID + id + COULD_BE_FOUND));

        return mapper.drinkToDto(drink);
    }

    public List<DrinkDto> getByTitle(String title) throws VMSNotFoundException {
        List<Drink> foundDrinks = drinkRepository.findByTitle(title)
                .orElseThrow(() -> new VMSNotFoundException("No drink with title " + title + COULD_BE_FOUND));

        return foundDrinks.stream()
                .map(drink -> mapper.drinkToDto(drink))
                .collect(Collectors.toList());
    }

    public List<DrinkDto> getByMachine(Long machineId) throws VMSNotFoundException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new VMSNotFoundException("No machine with id " + machineId + COULD_BE_FOUND));

        List<Drink> foundDrinks = drinkRepository.findByMachine(machine)
                .orElseThrow(() -> new VMSNotFoundException("No drink in machine with id " + machineId + COULD_BE_FOUND));

        return foundDrinks.stream()
                .map(drink -> mapper.drinkToDto(drink))
                .collect(Collectors.toList());
    }

    public List<DrinkDto> getByTitleAndMachine(String title, Long machineId) throws VMSNotFoundException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new VMSNotFoundException("No machine with id " + machineId + COULD_BE_FOUND));

        List<Drink> foundDrinks = drinkRepository.findByTitleAndMachine(title, machine)
                .orElseThrow(() -> new VMSNotFoundException("No drink with title " + title +
                        " in machine with id " + machineId + COULD_BE_FOUND));

        return foundDrinks.stream()
                .map(drink -> mapper.drinkToDto(drink))
                .collect(Collectors.toList());
    }

    private void validateDrink(DrinkDto drinkDto) throws VMSValidationException {
        if (StringUtils.isEmpty(drinkDto.getTitle())) {
            throw new VMSValidationException("Title cannot be empty");
        }
        if (StringUtils.isEmpty(drinkDto.getVolume())) {
            throw new VMSValidationException("Volume cannot be empty");
        }
        if (StringUtils.isEmpty(drinkDto.getMachineId())) {
            throw new VMSValidationException("Machine id cannot be empty");
        }
    }

    private Machine getMachineById(Long id) throws VMSNotFoundException {

        return machineRepository.findById(id)
                .orElseThrow(() -> new VMSNotFoundException("Machine with id " + id + " does not exist"));
    }

}
