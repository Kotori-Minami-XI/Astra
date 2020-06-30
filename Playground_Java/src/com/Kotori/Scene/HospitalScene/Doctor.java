package com.Kotori.Scene.HospitalScene;

import java.util.LinkedList;
import java.util.Queue;

class Doctor{
    private String doctorName;
    private Queue<Ticket> ticketList = new LinkedList();
    public Doctor(String doctorName, int capacity) {
        this.doctorName = doctorName;
        for (int i=0; i < capacity; i++) {
            ticketList.offer(new Ticket(this, "ticket" + i ));
        }
    }

    public Ticket removeTicket(DoctorDashboard dashboard) {
        synchronized (ticketList) {
            if (ticketList.size() == 0) {
                return null;
            } else if (ticketList.size() == 1) {
                // 若是最后一个元素，则需要通知dashboard
                dashboard.updateRemainingDoctor(-1);
            }
            return ticketList.poll();
        }
    }

    public Boolean isTicketEmpty() {
        return (ticketList.size() == 0);
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
