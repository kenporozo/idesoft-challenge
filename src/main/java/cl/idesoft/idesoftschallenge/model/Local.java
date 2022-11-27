package cl.idesoft.idesoftschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Local {

    @JsonProperty("local_nombre")
    private String name;

    @JsonProperty("comuna_nombre")
    private String commune;

    @JsonProperty("local_direccion")
    private String address;

    @JsonProperty("local_telefono")
    private String phone;

}
