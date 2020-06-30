package com.Kotori.Scene.HospitalScene;

import java.util.LinkedList;
import java.util.Queue;

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

    public Ticket removeTicket(DoctorDashboard dashboard) {
//        synchronized (ticketList) {
//            if (ticketList.size() == 0) {
//                return null;
//            }
//            return ticketList.poll();
//        }
        if (ticketList.size() == 0) {
            return null;
        } else if (ticketList.size() == 1) {
            // 取出最后一个元素，更新dashboard
            dashboard.updateRemainingDoctor(-1);
        }
        return ticketList.poll();
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
