package com.Kotori.Playground.DesignMode;

public class DutyChainMode {
    public static void main(String[] args) {
        ProjectManager projectManager = new ProjectManager();
        DepartmentManager departmentManager = new DepartmentManager();
        GeneralManager generalManager = new GeneralManager();
        projectManager.setSuccessor(departmentManager);
        departmentManager.setSuccessor(generalManager);

        String msg = projectManager.requestFee(700);
        System.out.println(msg);
    }
}

abstract class FeeHandler {
    private FeeHandler successor;

    public abstract String requestFee(Integer fee);

    public void setSuccessor(FeeHandler successor) {
        this.successor = successor;
    }

    public FeeHandler getSuccessor() {
        return successor;
    }
}

class ProjectManager extends FeeHandler{
    @Override
    public String requestFee(Integer fee) {
        if (fee <= 500) {
            return "ProjectManager has dealt with the request";
        }
        if (this.getSuccessor() == null) {
            return "Request not granted!";
        }
        return this.getSuccessor().requestFee(fee);
    }
}

class DepartmentManager extends FeeHandler{
    @Override
    public String requestFee(Integer fee) {
        if (fee <= 1500) {
            return "DepartmentManager has dealt with the request";
        }
        if (this.getSuccessor() == null) {
            return "Request not granted!";
        }
        return this.getSuccessor().requestFee(fee);
    }
}

class GeneralManager extends FeeHandler{
    @Override
    public String requestFee(Integer fee) {
        if (fee <= 2500) {
            return "GeneralManager has dealt with the request";
        }
        if (this.getSuccessor() == null) {
            return "Request not granted!";
        }
        return this.getSuccessor().requestFee(fee);
    }
}