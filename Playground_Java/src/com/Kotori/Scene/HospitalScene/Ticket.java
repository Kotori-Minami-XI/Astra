package com.Kotori.Scene.HospitalScene;

class Ticket {
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
