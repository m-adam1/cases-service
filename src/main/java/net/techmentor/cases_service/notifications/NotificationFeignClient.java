package net.techmentor.cases_service.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notifications-service", url = "${notifications-service.url}")
public interface NotificationFeignClient extends NotificationApi {
    
    @Override
    @PostMapping("/v1/notifications/devices")
    void notifyDevices(
        @RequestBody List<String> tokens,
        @RequestParam("title") String title,
        @RequestParam("body") String body
    );

    @Override
    @PostMapping("/v1/notifications/topics/{topic}")
    void notifyTopicSubscribers(
        @PathVariable("topic") String topic,
        @RequestParam("event") String event,
        @RequestBody Object extraJsonData,
        @RequestParam("title") String title,
        @RequestParam("body") String body
    );

    @Override
    @PostMapping("/v1/notifications/topics/{topic}/subscribe")
    void subscribeToTopic(
        @PathVariable("topic") String topic,
        @RequestBody List<String> tokens
    );
} 
