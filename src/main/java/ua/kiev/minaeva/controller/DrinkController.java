package ua.kiev.minaeva.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ua.kiev.minaeva.dto.DrinkDto;
import ua.kiev.minaeva.exception.VMSNotFoundException;
import ua.kiev.minaeva.exception.VMSValidationException;
import ua.kiev.minaeva.service.DrinkService;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
@Log
@CrossOrigin
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping
    public List<DrinkDto> getAllDrinks() {
        log.info("handling GET ALL drinks request");
        return drinkService.getAll();
    }

    @GetMapping("/{drinkId}")
    public DrinkDto getDrinkById(@PathVariable final Long drinkId) throws VMSNotFoundException {
        log.info("handling GET DRINK BY ID request: " + drinkId);
        return drinkService.getById(drinkId);
    }

    @GetMapping("/title/{title}")
    public List<DrinkDto> getByTitle(@PathVariable final String title) throws VMSNotFoundException {
        log.info("handling GET DRINK BY TITLE request: " + title);
        return drinkService.getByTitle(title);
    }

    @GetMapping("/title/{title}/machine/{machineId}")
    public List<DrinkDto> getByTitleAndMachineId(@PathVariable final String title,
                                                 @PathVariable final Long machineId) throws VMSNotFoundException {
        log.info("handling GET DRINK BY TITLE AND MACHINE ID request: " + title + " " + machineId);
        return drinkService.getByTitleAndMachine(title, machineId);
    }

    @PostMapping
    public DrinkDto createDrink(@RequestBody DrinkDto drinkDto) throws VMSNotFoundException, VMSValidationException {
        log.info("handling CREATE DRINK request: " + drinkDto);
        return drinkService.addDrink(drinkDto);
    }

    @PutMapping
    public DrinkDto updateDrink(@RequestBody DrinkDto drinkDto) throws VMSValidationException, VMSNotFoundException {
        log.info("handling UPDATE DRINK request: " + drinkDto);
        return drinkService.updateDrink(drinkDto);
    }

}
