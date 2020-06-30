package com.Kotori.Scene.HospitalScene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorDashboard {
    private List<Doctor> doctorList = new ArrayList();
    private int nrOfDoctor;
    private AtomicInteger remainingDoctor;

    public DoctorDashboard(int nrOfDoctor, int nrOfTicketPerDoctor) {
        this.nrOfDoctor = nrOfDoctor;
        remainingDoctor = new AtomicInteger(nrOfDoctor);
        for (int i = 0; i < nrOfDoctor; i++) {
            doctorList.add(new Doctor("Doctor" + i, nrOfTicketPerDoctor));
        }
    }

    public int getNrOfDoctor() {
        return nrOfDoctor;
    }

    public Doctor getDoctorByIndex(int index) {
        return this.doctorList.get(index);
    }

    public Boolean isAllTicketSold() {
        return (remainingDoctor.get() == 0);
    }

    public void updateRemainingDoctor(int delta) {
        this.remainingDoctor.addAndGet(delta);
    }
}
