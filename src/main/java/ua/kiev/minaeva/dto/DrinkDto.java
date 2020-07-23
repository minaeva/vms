package ua.kiev.minaeva.dto;

import lombok.Data;

@Data
public class DrinkDto {

    private Long id;
    private String title;
    //todo split size into qty:int and units:enum
    private String volume;
    private Long machineId;

}
