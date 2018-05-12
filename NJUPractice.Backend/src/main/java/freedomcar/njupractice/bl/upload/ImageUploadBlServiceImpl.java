package freedomcar.njupractice.bl.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import trapx00.tagx00.blservice.upload.MissionUploadBlService;
import trapx00.tagx00.dataservice.mission.RequesterMissionDataService;
import trapx00.tagx00.dataservice.upload.ImageDataService;
import trapx00.tagx00.entity.mission.ImageMission;
import trapx00.tagx00.exception.viewexception.MissionIdDoesNotExistException;
import trapx00.tagx00.exception.viewexception.SystemException;
import trapx00.tagx00.publicdatas.mission.MissionType;
import trapx00.tagx00.response.upload.UploadMissionImageResponse;

import java.io.IOException;
import java.util.List;


@Service
public class ImageUploadBlServiceImpl implements MissionUploadBlService {

    private final ImageDataService imageDataService;
    private final RequesterMissionDataService requesterMissionDataService;

    @Autowired
    public ImageUploadBlServiceImpl(ImageDataService imageDataService, RequesterMissionDataService requesterMissionDataService) {
        this.imageDataService = imageDataService;
        this.requesterMissionDataService = requesterMissionDataService;
    }

    /**
     * Upload the image of the mission
     *
     * @param missionId
     * @param multipartFile
     * @param order
     * @param isCover
     * @return the url of the image
     */
    @Override
    public UploadMissionImageResponse uploadFiles(String missionId, MultipartFile multipartFile, int order, boolean isCover) throws SystemException, MissionIdDoesNotExistException {
        try {
            ImageMission imageMission = (ImageMission) requesterMissionDataService.getMissionByMissionId(missionId, MissionType.IMAGE);
            if (imageMission != null) {
                String url = imageDataService.uploadImage(generateImageKey(missionId, order, isCover), multipartFile.getBytes());
                List<String> urls = imageMission.getImageUrls();
                if (isCover) {
                    imageMission.setCoverUrl(url);
                } else {
                    urls.add(url);
                    imageMission.setImageUrls(urls);
                }
                requesterMissionDataService.updateMission(imageMission);
                return new UploadMissionImageResponse(url);

            } else {
                throw new MissionIdDoesNotExistException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException();
        }
    }

    private String generateImageKey(String missionId, int order, boolean isCover) {
        if (isCover) {
            return "image_cover" + missionId;
        } else {
            return "image_" + missionId + "_" + order;
        }
    }
}
