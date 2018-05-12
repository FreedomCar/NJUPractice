package freedomcar.njupractice.data.dao.mission.instance;

import org.springframework.data.jpa.repository.JpaRepository;
import trapx00.tagx00.entity.mission.instance.ImageInstance;

import java.util.ArrayList;

public interface ImageInstanceDao extends JpaRepository<ImageInstance, String> {
    ArrayList<ImageInstance> findImageInstancesByMissionId(String missionId);

    ArrayList<ImageInstance> findImageInstancesByWorkerUsername(String workerUsername);

    ImageInstance findImageInstanceByInstanceId(String instanceId);
}
