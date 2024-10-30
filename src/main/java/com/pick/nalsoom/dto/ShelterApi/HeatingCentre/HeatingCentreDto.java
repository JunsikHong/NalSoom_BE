package com.pick.nalsoom.dto.ShelterApi.HeatingCentre;

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
