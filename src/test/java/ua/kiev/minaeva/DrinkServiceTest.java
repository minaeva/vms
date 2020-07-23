package ua.kiev.minaeva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kiev.minaeva.dto.DrinkDto;
import ua.kiev.minaeva.dto.MachineDto;
import ua.kiev.minaeva.entity.Drink;
import ua.kiev.minaeva.entity.Machine;
import ua.kiev.minaeva.exception.VMSNotFoundException;
import ua.kiev.minaeva.exception.VMSValidationException;
import ua.kiev.minaeva.mapper.DrinkMapper;
import ua.kiev.minaeva.mapper.MachineMapper;
import ua.kiev.minaeva.repository.DrinkRepository;
import ua.kiev.minaeva.repository.MachineRepository;
import ua.kiev.minaeva.service.impl.DrinkServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DrinkServiceTest {

    private static Drink aDrink;
    private static DrinkDto aDrinkDto;
    private static Machine aMachine;
    private static MachineDto aMachineDto;
    @InjectMocks
    public DrinkServiceImpl drinkService;
    @Mock
    private DrinkRepository drinkRepository;
    @Mock
    private MachineRepository machineRepository;
    private DrinkMapper drinkMapper = Mappers.getMapper(DrinkMapper.class);
    private MachineMapper machineMapper = Mappers.getMapper(MachineMapper.class);


    @BeforeEach
    void setup() {
        aMachine = new Machine();
        aMachine.setId(1L);
        aMachine.setAddress("Chernistsy, 24");

        aDrink = new Drink();
        aDrink.setTitle("cola");
        aDrink.setVolume("330ml");
        aDrink.setMachine(aMachine);

        aDrinkDto = drinkMapper.drinkToDto(aDrink);
        aMachineDto = machineMapper.machineToDto(aMachine);
    }

    @Test
    void createDrink_successful() throws VMSValidationException, VMSNotFoundException {
        when(drinkRepository.save(any())).thenReturn(aDrink);
        when(machineRepository.findById(anyLong())).thenReturn(Optional.of(aMachine));

        DrinkDto createdDrink = drinkService.addDrink(aDrinkDto);

        assertThat(createdDrink).isNotNull();
        assertThat(createdDrink.getTitle()).isEqualTo(aDrink.getTitle());
    }

    @Test
    void createDrink_failedOnEmptyTitle() {
        aDrinkDto.setTitle("");

        assertThrows(VMSValidationException.class,
                () -> drinkService.addDrink(aDrinkDto));
    }

}
