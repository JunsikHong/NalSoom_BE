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

    @JsonProperty("R_SEQ_NO")
    private String rSeqNo;

    @JsonProperty("YEAR")
    private String year;

    @JsonProperty("AREA_CD")
    private String areaCd;

    @JsonProperty("EQUP_TYPE")
    private String equpType;

    @JsonProperty("R_AREA_NM")
    private String rAreaNm;

    @JsonProperty("R_DETL_ADD")
    private String rDetlAdd;

    @JsonProperty("J_DETL_ADD")
    private String jDetlAdd;

    @JsonProperty("R_AREA_SQR")
    private String rAreaSqr;

    @JsonProperty("USE_PRNB")
    private String usePrnb;

    @JsonProperty("CLER1_CNT")
    private String cler1Cnt;

    @JsonProperty("CLER2_CNT")
    private String cler2Cnt;

    @JsonProperty("CHK1_YN")
    private String chk1Yn;

    @JsonProperty("CHK2_YN")
    private String chk2Yn;

    @JsonProperty("CHK3_YN")
    private String chk3Yn;

    @JsonProperty("CRE_DTTM")
    private String creDttm;

    @JsonProperty("UPDT_DTTM")
    private String updtDttm;

    @JsonProperty("USE_YN")
    private String useYn;

    @JsonProperty("RMRK")
    private String rmrk;

    @JsonProperty("DT_START")
    private String dtStart;

    @JsonProperty("DT_END")
    private String dtEnd;

    @JsonProperty("LO")
    private String lo;

    @JsonProperty("LA")
    private String la;

    @JsonProperty("XX")
    private String xx;

    @JsonProperty("YY")
    private String yy;

}
