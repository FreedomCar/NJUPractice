package freedomcar.njupractice.response.user;

import trapx00.tagx00.response.Response;
import trapx00.tagx00.vo.user.info.WorkerInfoVo;

public class WorkerInfoResponse extends Response {
    private WorkerInfoVo info;

    public WorkerInfoResponse() {
    }

    public WorkerInfoResponse(WorkerInfoVo info) {
        this.info = info;
    }

    public WorkerInfoVo getInfo() {
        return info;
    }

    public void setInfo(WorkerInfoVo info) {
        this.info = info;
    }
}
