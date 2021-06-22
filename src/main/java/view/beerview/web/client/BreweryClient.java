package view.beerview.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import view.beerview.web.model.BeerDto;
import org.springframework.web.client.RestTemplate;


import java.util.UUID;

@Component
@ConfigurationProperties(value = "ms.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apihost;
    public final String BEER_PATH_V1 = "/api/v1/beer/";


    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public void setApihost(String apihost){
        this.apihost = apihost;
    }


}
