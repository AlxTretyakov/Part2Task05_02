package inno.tretyakov.workers;

import inno.tretyakov.dto.AgreementDto;
import inno.tretyakov.dto.InstanceDto;
import inno.tretyakov.dto.RespInstanceDto;
import inno.tretyakov.entity.Agreement;
import inno.tretyakov.entity.TppProduct;
import inno.tretyakov.exceptions.InvalidRequest;
import inno.tretyakov.exceptions.NoData;
import inno.tretyakov.interfaces.InstanceProcessable;
import inno.tretyakov.repository.AgreementRepository;
import inno.tretyakov.repository.TppProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InstancePresents implements InstanceProcessable {
    @Autowired
    TppProductRepository tppProductRepository;

    @Autowired
    AgreementRepository agreementRepository;

    @Override
    public void process(InstanceDto instanceDto, RespInstanceDto responseInstanceDto) {
        Optional<TppProduct> tppProductOptional = tppProductRepository.findById(Integer.valueOf(instanceDto.getInstanceId().toString()));
        if(tppProductOptional.isEmpty()){
            throw new NoData("Продукт с ID "+ instanceDto.getInstanceId()+" не найден!");
        }

        List<AgreementDto> agreementDtos = instanceDto.getInstanceArrangement();
        agreementDtos.forEach(it -> {
            System.out.println(it.getNumber());
            List<Agreement> agreements = agreementRepository.findFirstByNumber(it.getNumber());
            if (!agreements.isEmpty()) {
                throw new InvalidRequest("Дополнительное соглашение уже существует!");
            }
        });

        tppProductOptional = tppProductRepository.findById(Integer.valueOf(instanceDto.getInstanceId().toString()));
        List<String> agreementsIdList = new ArrayList<>();
        TppProduct tppProduct = tppProductOptional.get();
        agreementDtos = instanceDto.getInstanceArrangement();
        agreementDtos.forEach(it -> {
            Agreement agreement = new Agreement(null, tppProduct,
                    it.getGeneralAgreementId(),
                    it.getSupplementaryAgreementId(),
                    it.getArrangementType(),
                    it.getShedulerJobId(),
                    it.getNumber(),
                    it.getOpeningDate(),
                    it.getClosingDate(),
                    it.getCancelDate(),
                    it.getValidityDuration(),
                    it.getCancellationReason(),
                    it.getStatus(),
                    it.getInterestCalculationDate(),
                    it.getInterestRate(),
                    it.getCoefficient(),
                    it.getCoefficientAction(),
                    it.getMinimumInterestRate(),
                    it.getMinimumInterestRateCoefficient(),
                    it.getMinimumInterestRateCoefficientAction(),
                    it.getMaximalnterestRate(),
                    it.getMaximalnterestRateCoefficient(),
                    it.getMaximalnterestRateCoefficientAction()
            );

            agreementRepository.save(agreement);
            agreementsIdList.add(agreement.getId().toString());
        });

        responseInstanceDto.setSupplementaryAgreementId(agreementsIdList);

    }
}
