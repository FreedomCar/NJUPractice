package freedomcar.njupractice.response.user;

import trapx00.tagx00.response.Response;
import trapx00.tagx00.vo.mission.requester.MissionDateNumVo;

import java.util.ArrayList;

public class AdminInfoResponse extends Response {
    private int userCount;
    private int totalMissionCount;
    private int pendingMissionCount;
    private int activeMissionCount;
    private int endedMissionCount;
    private int totalInstanceCount;
    private int inProgressInstanceCount;
    private int submittedInstanceCount;
    private int finalizeInstanceCount;
    private ArrayList<MissionDateNumVo> listOfInstanceDateNum;

    public AdminInfoResponse() {
    }

    public AdminInfoResponse(int userCount, int totalMissionCount, int pendingMissionCount, int activeMissionCount, int endedMissionCount, int totalInstanceCount, int inProgressInstanceCount, int submittedInstanceCount, int finalizeInstanceCount, ArrayList<MissionDateNumVo> missionDateNumVos) {
        this.userCount = userCount;
        this.totalMissionCount = totalMissionCount;
        this.pendingMissionCount = pendingMissionCount;
        this.activeMissionCount = activeMissionCount;
        this.endedMissionCount = endedMissionCount;
        this.totalInstanceCount = totalInstanceCount;
        this.inProgressInstanceCount = inProgressInstanceCount;
        this.submittedInstanceCount = submittedInstanceCount;
        this.finalizeInstanceCount = finalizeInstanceCount;
        this.listOfInstanceDateNum = missionDateNumVos;
    }

    public int getPendingMissionCount() {
        return pendingMissionCount;
    }

    public void setPendingMissionCount(int pendingMissionCount) {
        this.pendingMissionCount = pendingMissionCount;
    }

    public int getActiveMissionCount() {
        return activeMissionCount;
    }

    public void setActiveMissionCount(int activeMissionCount) {
        this.activeMissionCount = activeMissionCount;
    }

    public int getEndedMissionCount() {
        return endedMissionCount;
    }

    public void setEndedMissionCount(int endedMissionCount) {
        this.endedMissionCount = endedMissionCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getTotalMissionCount() {
        return totalMissionCount;
    }

    public void setTotalMissionCount(int totalMissionCount) {
        this.totalMissionCount = totalMissionCount;
    }

    public int getTotalInstanceCount() {
        return totalInstanceCount;
    }

    public void setTotalInstanceCount(int totalInstanceCount) {
        this.totalInstanceCount = totalInstanceCount;
    }

    public int getInProgressInstanceCount() {
        return inProgressInstanceCount;
    }

    public void setInProgressInstanceCount(int inProgressInstanceCount) {
        this.inProgressInstanceCount = inProgressInstanceCount;
    }

    public int getSubmittedInstanceCount() {
        return submittedInstanceCount;
    }

    public void setSubmittedInstanceCount(int submittedInstanceCount) {
        this.submittedInstanceCount = submittedInstanceCount;
    }

    public int getFinalizeInstanceCount() {
        return finalizeInstanceCount;
    }

    public void setFinalizeInstanceCount(int finalizeInstanceCount) {
        this.finalizeInstanceCount = finalizeInstanceCount;
    }

    public ArrayList<MissionDateNumVo> getListOfInstanceDateNum() {
        return listOfInstanceDateNum;
    }

    public void setListOfInstanceDateNum(ArrayList<MissionDateNumVo> listOfInstanceDateNum) {
        this.listOfInstanceDateNum = listOfInstanceDateNum;
    }
}
