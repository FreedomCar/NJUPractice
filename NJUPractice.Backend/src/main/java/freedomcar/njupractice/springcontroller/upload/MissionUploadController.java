package freedomcar.njupractice.springcontroller.upload;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trapx00.tagx00.blservice.upload.MissionUploadBlService;
import trapx00.tagx00.entity.account.Role;
import trapx00.tagx00.exception.viewexception.MissionIdDoesNotExistException;
import trapx00.tagx00.exception.viewexception.SystemException;
import trapx00.tagx00.response.Response;
import trapx00.tagx00.response.WrongResponse;
import trapx00.tagx00.response.upload.UploadMissionImageResponse;

@PreAuthorize(value = "hasRole('" + Role.REQUESTER_NAME + "')")
@RestController
public class MissionUploadController {
    private final MissionUploadBlService missionUploadBlService;

    @Autowired
    public MissionUploadController(MissionUploadBlService missionUploadBlService) {
        this.missionUploadBlService = missionUploadBlService;
    }


    @Authorization(value = "发布者")
    @ApiOperation(value = "发布者上传文件", notes = "发布者上传本次任务的图片，传输时限为10min")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "图片", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "order", value = "图片顺序", required = true, dataType = "int"),
            @ApiImplicitParam(name = "isCover", value = "图片是否是封面图", required = true, dataType = "bool"),
            @ApiImplicitParam(name = "missionId", value = "任务ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/upload/mission/image/{missionId}", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Image uploaded", response = UploadMissionImageResponse.class),
            @ApiResponse(code = 403, message = "Upload session timed out", response = WrongResponse.class),
            @ApiResponse(code = 404, message = "Upload session id not exist", response = WrongResponse.class),
            @ApiResponse(code = 503, message = "Failure", response = WrongResponse.class)
    })
    public ResponseEntity<Response> uploadFiles(@PathVariable("missionId") String missionId, @RequestParam("files[]") MultipartFile multipartFile, @RequestParam("order") int order, @RequestParam("isCover") boolean isCover) {
        try {
            return new ResponseEntity<>(missionUploadBlService.uploadFiles(missionId, multipartFile, order, isCover), HttpStatus.CREATED);
        } catch (SystemException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponse(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (MissionIdDoesNotExistException e) {
            return new ResponseEntity<>(e.getResponse(), HttpStatus.NOT_FOUND);
        }
    }
}
