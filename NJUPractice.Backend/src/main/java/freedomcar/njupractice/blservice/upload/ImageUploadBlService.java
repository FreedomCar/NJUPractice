package freedomcar.njupractice.blservice.upload;

import org.springframework.web.multipart.MultipartFile;
import trapx00.tagx00.exception.viewexception.MissionIdDoesNotExistException;
import trapx00.tagx00.exception.viewexception.SystemException;
import trapx00.tagx00.response.upload.UploadMissionImageResponse;

public interface ImageUploadBlService {
    /**
     * Upload the image of the mission
     *
     * @param missionId
     * @param multipartFile
     * @param order
     * @param isCoverString
     * @return the url of the image
     */
    UploadMissionImageResponse uploadFiles(String missionId, MultipartFile multipartFile, int order, boolean isCover) throws SystemException, MissionIdDoesNotExistException;
}
