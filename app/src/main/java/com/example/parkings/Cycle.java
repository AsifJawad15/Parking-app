package com.example.parkings;

public class Cycle {
    public String userId;
    public String cycleId;
    public String pin;
    public String status; // "In" or "Out"

    public Cycle(String userId, String cycleId, String pin, String status) {
        this.userId = userId;
        this.cycleId = cycleId;
        this.pin = pin;
        this.status = status;
    }

    public void toggleStatus() {
        this.status = this.status.equals("In") ? "Out" : "In";
    }
}
