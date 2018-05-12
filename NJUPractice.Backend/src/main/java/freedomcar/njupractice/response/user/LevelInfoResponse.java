package freedomcar.njupractice.response.user;

import trapx00.tagx00.response.Response;

public class LevelInfoResponse extends Response {
    private int[] levels;

    public LevelInfoResponse() {
    }

    public LevelInfoResponse(int[] levels) {
        this.levels = levels;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }
}
