package cat.itacademy.barcelonactiva.BiergeOscar.s05t01n03.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlorDTO {

    private Long pk_FlorID;
    private String nomFlor;
    private String paisFlor;
    private String tipusFlor;
}
