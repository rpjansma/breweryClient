package view.beerview.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import view.beerview.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "ms.customer", ignoreUnknownFields = false)
public class CustomerClient {

    private String apihost;
    public final String BEER_PATH_V1 = "/api/v1/customer/";


    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID id) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + id.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, customerDto);
    }


    public void updateCustomer(UUID customerId, CustomerDto customerDto){
        restTemplate.put(apihost + BEER_PATH_V1 + customerId.toString(), customerDto);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apihost + BEER_PATH_V1 + customerId.toString());
    }

    public void setApihost(String apihost){
        this.apihost = apihost;
    }


}
