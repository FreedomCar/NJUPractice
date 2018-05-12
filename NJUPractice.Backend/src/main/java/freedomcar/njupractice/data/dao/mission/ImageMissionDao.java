package freedomcar.njupractice.data.dao.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import trapx00.tagx00.entity.mission.ImageMission;

import java.util.ArrayList;

public interface ImageMissionDao extends JpaRepository<ImageMission, String> {
    ImageMission findImageMissionByMissionId(String missionId);

    ArrayList<ImageMission> findImageMissionsByRequesterUsername(String requesterUsername);
}
