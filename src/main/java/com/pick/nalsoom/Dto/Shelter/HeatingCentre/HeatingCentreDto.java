package com.pick.nalsoom.Dto.Shelter.HeatingCentre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeatingCentreDto {

    @JsonProperty("TbGtnCwP")
    private TbGtnCwPDto tbGtnCwP;
}
