package com.Kotori.Scene;

import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static com.Kotori.Scene.HospitalScene.workStations;

class Doctor{
    private String doctorName;
    private Queue<Ticket> ticketList = new LinkedList();
    public Doctor(String doctorName, int capacity) {
        this.doctorName = doctorName;
        for (int i=0; i < capacity; i++) {
            ticketList.offer(new Ticket(this, "ticket" + i ));
        }
    }

    synchronized public Ticket removeTicket() {
        if (ticketList.size() == 0) {
            return null;
        }
        return ticketList.poll();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}

class Ticket{
    private Doctor doctor;
    private String ticketName;

    public Ticket(Doctor doctor, String ticketName) {
        this.doctor = doctor;
        this.ticketName = ticketName;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTicketName() {
        return this.doctor.getDoctorName() + "的" + this.ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
}

class WorkStation implements Callable<Ticket> {
    private List<Doctor> doctorList = new ArrayList();
    private String WorkStationName;

    public WorkStation(String workStationName) {
        WorkStationName = workStationName;
    }

    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    public Ticket offerTicket() {
        Random random = new Random();
        int doctorIndex = random.nextInt(doctorList.size());
        return doctorList.get(doctorIndex).removeTicket();
    }

    @Override
    public Ticket call() throws Exception {
        Thread.sleep(2000);//隔几秒发一张票
        workStations.put(this);
        return offerTicket();
    }
}

class Patient {
    private String patientName;

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void requestTicket() {
        try {
            WorkStation workStation = workStations.take();
            Ticket ticket = workStation.offerTicket();
            System.out.println(this.patientName + "得到了" + ticket.getTicketName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class HospitalScene {
    static BlockingQueue<WorkStation> workStations = new LinkedBlockingQueue();

    @Test
    public void testBlockingQueue() {
        int workStationNum = 4;
        int doctorNum = 5;
        int patientNum = 1000;

        for (int i = 0; i < workStationNum; i++) {
            WorkStation workStation = new WorkStation("WorkStation" + i);
            for (int j = 0; j < doctorNum; j++) {
                workStation.addDoctor(new Doctor("Doctor"+j, 5));
            }
            workStations.offer(workStation);
        }

        for (int i = 0; i < patientNum; i++) {
            new Patient("patient"+i).requestTicket();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
