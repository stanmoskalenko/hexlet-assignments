package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GuestDTO {
    @NotNull
    private long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 11, max = 13)
    @Pattern(regexp = "(^\\+)\\d+")
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;

    private LocalDate createdAt;
}