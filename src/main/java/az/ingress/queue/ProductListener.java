package az.ingress.queue;

import az.ingress.mapper.factory.ObjectMapperFactory;
import az.ingress.model.dto.RatingQueueDto;
import az.ingress.service.abstraction.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static az.ingress.mapper.factory.ObjectMapperFactory.OBJECT_MAPPER_FACTORY;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListener {
    private final ProductService productService;

    @RabbitListener(queues = "RATING_Q")
    public void consume(String message) {
        try {
            var data = OBJECT_MAPPER_FACTORY.crateObjectMapper().readValue(message, RatingQueueDto.class);
            productService.updateRating(data);
            log.info("Product Rating upodated!");
        } catch (JsonProcessingException ex) {
            log.error("ActionLog.consume.error message invalid format : {}", message);

        } catch (Exception ex) {
            log.error("Unexpected exceptionnnnnnnnnnnnnnn!!!!!");
        }
    }
}
