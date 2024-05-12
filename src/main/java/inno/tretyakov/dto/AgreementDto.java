package inno.tretyakov.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class AgreementDto implements Serializable {
    private final Integer id;
    private final TppProductDto product_id;
    private final String GeneralAgreementId; //general_agreement_id;
    private final String SupplementaryAgreementId; //supplementary_agreement_id;
    private final String arrangementType; //arrangement_type;
    private final Long shedulerJobId; //sheduler_job_id;
    @NotBlank
    private final String Number; //number;
    @NotNull
    private final Timestamp openingDate; //opening_date;
    private final Timestamp closingDate; //closing_date;
    private final Timestamp CancelDate; //cancel_date;
    private final Long validityDuration; //validity_duration;
    private final String cancellationReason; //cancellation_reason;
    private final String Status; //status;
    private final Timestamp interestCalculationDate; //interest_calculation_date;
    private final Double interestRate; //interest_rate;
    private final Double coefficient; //coefficient;
    private final String coefficientAction; //coefficient_action;
    private final Double minimumInterestRate; //minimum_interest_rate;
    private final Double minimumInterestRateCoefficient; //minimum_interest_rate_coefficient;
    private final String minimumInterestRateCoefficientAction; //minimum_interest_rate_coefficient_action;
    private final Double maximalnterestRate; //maximal_interest_rate;
    private final Double maximalnterestRateCoefficient; //maximal_interest_rate_coefficient;
    private final String maximalnterestRateCoefficientAction; //maximal_interest_rate_coefficient_action;
}
