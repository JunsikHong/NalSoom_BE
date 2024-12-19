package com.pick.nalsoom.dto.ShelterApi.CoolingCentre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RowDto {

    @JsonProperty("YR")
    private String yr;

    @JsonProperty("RGN_CD")
    private String rgnCd;

    @JsonProperty("FCLT_TYPE")
    private String fcltType;

    @JsonProperty("RESTAREA_NM")
    private String restareaNm;

    @JsonProperty("ROAD_NM_ADDR")
    private String roadNmAddr;

    @JsonProperty("LOTNO_DADDR")
    private String lotnoDaddr;

    @JsonProperty("AREA")
    private String area;

    @JsonProperty("UTZTN_PSBLTY_NOPE")
    private String utztnPsbltyNope;

    @JsonProperty("RMRK")
    private String rmrk;

    @JsonProperty("OPER_BGNG_YMD")
    private String operBgngYmd;

    @JsonProperty("OPER_END_YMD")
    private String operEndYmd;

    @JsonProperty("LOT")
    private String lot;

    @JsonProperty("LAT")
    private String lat;

    @JsonProperty("XCRD")
    private String xcrd;

    @JsonProperty("YCRD")
    private String ycrd;

}
