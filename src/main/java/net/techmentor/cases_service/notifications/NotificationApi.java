package net.techmentor.cases_service.notifications;

import java.util.List;

public interface NotificationApi {
    void notifyDevices(List<String> tokens, String title, String body);

    void notifyTopicSubscribers(String topic, String event, Object extraJsonData, String title, String body);

    void subscribeToTopic(String topic, List<String> tokens);
}
