package freedomcar.njupractice.response.upload;

import trapx00.tagx00.response.Response;

public class UploadMissionImageResponse extends Response {
    private String url;

    public UploadMissionImageResponse() {
    }

    public UploadMissionImageResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
