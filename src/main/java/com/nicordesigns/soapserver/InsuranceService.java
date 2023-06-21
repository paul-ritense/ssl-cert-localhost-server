package com.nicordesigns.soapserver;

import com.ritense.soap.insurancetest.client.generated.InsuranceRequest;
import com.ritense.soap.insurancetest.client.generated.InsuranceResponse;

public interface InsuranceService {

    void writeInsuranceApplication(InsuranceRequest insuranceRequest);

    InsuranceResponse processInsuranceApplication(InsuranceRequest insuranceRequest);
}
