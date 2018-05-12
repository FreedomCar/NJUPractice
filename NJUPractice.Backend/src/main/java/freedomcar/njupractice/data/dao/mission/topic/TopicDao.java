package freedomcar.njupractice.data.dao.mission.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import trapx00.tagx00.entity.mission.topic.Topic;

public interface TopicDao extends JpaRepository<Topic, Integer> {
    Topic findTopicByValue(String value);
}
