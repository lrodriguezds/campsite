package com.lrodriguez.campsite.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ReservationDTO {

    private Long id;

    @Valid
    @NotNull
    private UserDto user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Field check_in is mandatory")
    @Future(message = "Field check_in must be a date in the future")
    private LocalDate checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Field check_out is mandatory")
    @Future(message = "Field check_out must be a date in the future")
    private LocalDate checkOut;

}
