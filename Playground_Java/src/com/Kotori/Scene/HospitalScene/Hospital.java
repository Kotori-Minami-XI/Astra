package com.Kotori.Scene.HospitalScene;

import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Hospital {
    static ExecutorService service;

    @Test
    public void testBlockingQueue() {
        int workStationNum = 4;
        int doctorNum = 3;
        int ticketNumPerDoc = 5;
        int patientNum = 100;

        DoctorDashboard dashboard = new DoctorDashboard(doctorNum, ticketNumPerDoc);

        // 初始化workStation, 用线程池来处理workStation的任务
        List<WorkStation> workStations = new ArrayList();
        service = Executors.newFixedThreadPool(workStationNum);
        for (int i = 0; i < workStationNum; i++) {
            WorkStation workStation = new WorkStation("WorkStation" + i, dashboard);
            service.execute(workStation);
            workStations.add(workStation);
        }

        // patient随机在各个workStation排队
        for (int i = 0; i < patientNum; i++) {
            Random random = new Random();
            int workStationIndex = random.nextInt(workStationNum);
            new Patient("patient"+i).requestTicket(workStations.get(workStationIndex));
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
