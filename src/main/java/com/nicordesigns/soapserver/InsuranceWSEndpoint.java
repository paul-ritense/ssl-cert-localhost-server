package com.nicordesigns.soapserver;

import com.ritense.soap.insurancetest.client.generated.InsuranceRequest;
import com.ritense.soap.insurancetest.client.generated.InsuranceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InsuranceWSEndpoint {

    private static final String NAMESPACE_URI = "http://www.nicordesigns.com/spring-ws-insurance";

    private InsuranceService insuranceService;

    @Autowired
    public InsuranceWSEndpoint(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "InsuranceRequest")
    @ResponsePayload
    public InsuranceResponse InsuranceApplication(@RequestPayload InsuranceRequest insuranceApplicationRequest) throws Exception {
        System.out.println("---InsuranceApplication---");
        if (insuranceApplicationRequest != null) {
            insuranceService.writeInsuranceApplication(insuranceApplicationRequest);
        }

        return insuranceService.processInsuranceApplication(insuranceApplicationRequest);
    }
}
