package ua.kiev.minaeva.service;

import ua.kiev.minaeva.dto.DrinkDto;
import ua.kiev.minaeva.exception.VMSNotFoundException;
import ua.kiev.minaeva.exception.VMSValidationException;

import java.util.List;

public interface DrinkService {

    DrinkDto addDrink(DrinkDto drink) throws VMSValidationException, VMSNotFoundException;

    DrinkDto updateDrink(DrinkDto drink) throws VMSValidationException, VMSNotFoundException;

    void deleteDrink(DrinkDto drinkDto) throws VMSNotFoundException;

    List<DrinkDto> getAll();

    DrinkDto getById(Long id) throws VMSNotFoundException;

    List<DrinkDto> getByTitle(String title) throws VMSNotFoundException;

    List<DrinkDto> getByMachine(Long machineId) throws VMSNotFoundException;

    List<DrinkDto> getByTitleAndMachine(String title, Long machineId) throws VMSNotFoundException;

}
