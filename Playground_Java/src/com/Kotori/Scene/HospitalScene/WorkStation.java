package com.Kotori.Scene.HospitalScene;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import static com.Kotori.Scene.HospitalScene.Hospital.service;

class WorkStation implements Runnable {
    private DoctorDashboard dashboard;
    private String WorkStationName;
    private LinkedBlockingQueue<Patient> waitingRoom = new LinkedBlockingQueue();

    public WorkStation(String workStationName, DoctorDashboard dashboard) {
        this.WorkStationName = workStationName;
        this.dashboard = dashboard;
    }

    public String getWorkStationName() {
        return WorkStationName;
    }

    public void enterWaitingRoom(Patient patient) throws InterruptedException {
        this.waitingRoom.put(patient);
    }

    public Ticket offerTicket() {
        Random random = new Random();
        int doctorIndex = random.nextInt(this.dashboard.getNrOfDoctor());
        Doctor doctor = dashboard.getDoctorByIndex(doctorIndex);

        synchronized (doctor) {
            Ticket ticket = doctor.removeTicket(this.dashboard);
            return ticket;
        }
    }

    @Override
    public void run() {
        try {
            if (dashboard.isAllTicketSold()) {
                System.out.println("所有票已经售完，关闭" + this.WorkStationName);
                return;
            }
            Patient patient = this.waitingRoom.take();
            Thread.sleep(500);//隔几秒发一张票
            Ticket ticket = offerTicket();
            if (ticket != null) {
                patient.obtainTicket(ticket);
            }
            service.execute(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
