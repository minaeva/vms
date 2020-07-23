package ua.kiev.minaeva.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.kiev.minaeva.dto.DrinkDto;
import ua.kiev.minaeva.entity.Drink;

@Mapper
public interface DrinkMapper {

    @Mapping(target = "machineId", expression = "java(drink.getMachine().getId())")
    DrinkDto drinkToDto(Drink drink);

    Drink dtoToDrink(DrinkDto drinkDto);

}
