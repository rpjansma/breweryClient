package view.beerview.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import view.beerview.web.model.BeerDto;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
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

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + id.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID id, BeerDto beerDto){
        restTemplate.put(apihost + BEER_PATH_V1 + id.toString(), beerDto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apihost + BEER_PATH_V1 + id.toString());
    }

    public void setApihost(String apihost){
        this.apihost = apihost;
    }


}
