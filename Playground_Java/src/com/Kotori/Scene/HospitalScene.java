package com.Kotori.Scene;

import com.Kotori.Playground.Thread.ThreadPool;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static com.Kotori.Scene.HospitalScene.workStations;

/***
 * 未完工
 */


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

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorName='" + doctorName + '\'' +
                ", ticketList=" + ticketList +
                '}';
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

    public String getTicketName() {
        return this.doctor.getDoctorName() + "的" + this.ticketName;
    }

    @Override
    public String toString() {
        return this.doctor.getDoctorName() + "的" + this.ticketName;
    }
}

class WorkStation implements Callable<Ticket> {
    private List<Doctor> doctorList = new ArrayList();
    private String WorkStationName;

    public WorkStation(String workStationName) {
        WorkStationName = workStationName;
    }

    public String getWorkStationName() {
        return WorkStationName;
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
        return offerTicket();
    }
}

class Patient {
    private String patientName;

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    public void requestTicket() {
        try {
            WorkStation workStation = workStations.take();

            FutureTask<Ticket> futureTask = new FutureTask(workStation);
            futureTask.run();
            Ticket ticket = futureTask.get();
            if (null == ticket) {
                System.out.println(workStation.getWorkStationName() + "发现已经没有票了");
            } else {
                System.out.println(this.patientName + "得到了" + ticket.getTicketName());
            }
            workStations.put(workStation);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
        int ticketNumPerDoc = 5;
        int patientNum = 100;

        List<Doctor> doctors = new ArrayList();
        for (int j = 0; j < doctorNum; j++) {
            doctors.add(new Doctor("Doctor"+j, ticketNumPerDoc));
        }

        for (int i = 0; i < workStationNum; i++) {
            WorkStation workStation = new WorkStation("WorkStation" + i);
            for (int j = 0; j < doctorNum; j++) {
                workStation.addDoctor(doctors.get(j));
            }
            workStations.offer(workStation);
        }

        //ExecutorService service = Executors.newFixedThreadPool(workStationNum);
        for (int i = 0; i < patientNum; i++) {
            new Patient("patient"+i).requestTicket();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInit() {
        int doctorNum = 5;
        for (int m = 0; m < doctorNum; m++) {
            Doctor doctor = new Doctor("Doctor" + m, 5);
            System.out.println(doctor.toString());
        }
    }
}
