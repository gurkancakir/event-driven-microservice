package com.gurkan.eventdrivenmicroservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    public TokenStore tokenStore(Serializer serializer, EntityManagerProvider entityManagerProvider) {
        return JpaTokenStore.builder()
                .entityManagerProvider(entityManagerProvider)
                .serializer(serializer)
                .build();
    }


    @Autowired
    public void configureProcessors(EventProcessingConfigurer eventProcessingConfigurer) {
        TrackingEventProcessorConfiguration tepConfig = TrackingEventProcessorConfiguration.forSingleThreadedProcessing().andInitialTrackingToken(StreamableMessageSource::createHeadToken);
        eventProcessingConfigurer.registerTrackingEventProcessorConfiguration(config -> tepConfig);
    }
}
