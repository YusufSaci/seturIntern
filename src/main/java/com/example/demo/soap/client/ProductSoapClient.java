package com.example.demo.soap.client;


import com.example.demo.soap.generated.GetProductRequest;
import com.example.demo.soap.generated.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
@RequiredArgsConstructor
public class ProductSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public GetProductResponse getProduct(Long id) {

        GetProductRequest request = new GetProductRequest();

        request.setId(id);

        return (GetProductResponse)
                webServiceTemplate.marshalSendAndReceive(
                        "http://localhost:8080/ws",
                        request
                );
    }
}