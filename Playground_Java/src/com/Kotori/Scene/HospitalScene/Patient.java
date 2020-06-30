package com.Kotori.Scene.HospitalScene;

import java.util.Random;

class Patient {
    private String patientName;

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    public void requestTicket(WorkStation workStation) {
        try {
            workStation.enterWaitingRoom(this);
        } catch (InterruptedException e) {
            System.out.println(this.patientName + "没有成功排队在" + workStation.getWorkStationName());
            e.printStackTrace();
        }
    }

    public void obtainTicket(Ticket ticket) {
        System.out.println(this.patientName + "获得了" + ticket.getTicketName());
    }
}
