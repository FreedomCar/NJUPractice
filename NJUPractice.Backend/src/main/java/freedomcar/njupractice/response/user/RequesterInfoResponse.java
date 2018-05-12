package freedomcar.njupractice.response.user;

import trapx00.tagx00.response.Response;
import trapx00.tagx00.vo.user.info.RequesterInfoVo;

public class RequesterInfoResponse extends Response {
    private RequesterInfoVo info;

    public RequesterInfoResponse() {
    }

    public RequesterInfoResponse(RequesterInfoVo info) {
        this.info = info;
    }

    public RequesterInfoVo getInfo() {
        return info;
    }

    public void setInfo(RequesterInfoVo info) {
        this.info = info;
    }
}
