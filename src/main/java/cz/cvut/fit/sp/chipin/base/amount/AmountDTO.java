package cz.cvut.fit.sp.chipin.base.amount;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AmountDTO {
    @NotBlank
    private String name;
    @NotNull
    private Float amount;
}
