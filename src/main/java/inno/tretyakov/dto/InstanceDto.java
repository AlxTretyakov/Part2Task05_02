package inno.tretyakov.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class InstanceDto implements Serializable {
    private Long instanceId;
    @NotBlank
    private String productType;
    @NotBlank
    private String productCode;
    @NotBlank
    private String registerType;
    @NotBlank
    private String mdmCode;
    @NotBlank
    private String contractNumber;
    @NotNull
    private Date contractDate;
    @NotNull
    private Integer priority;
    private Double interestRatePenalty;
    private Double minimalBalance;
    private Double thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private Double taxPercentageRate;
    private Double technicalOverdraftLimitAmount;
    @NotNull
    private Integer contractId;
    @NotBlank
    private String BranchCode;
    @NotBlank
    private String IsoCurrencyCode;
    @NotBlank
    private String urgencyCode;
    private Integer ReferenceCode;
    List<PrivatePropertiesDto> additionalPropertiesVip;
    List<AgreementDto> instanceArrangement;
}
