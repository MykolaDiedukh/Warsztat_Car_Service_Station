package station.service.car.model;

import java.util.Date;

public class Order {
    private int id;
    private Date accepted;
    private Date planted;
    private Date start;
    private String descProblem;
    private String descRepair;
    private int status;
    private double repairCost;
    private double partsCost;
    private int hourlyRate;
    private double repairHours;
    private int employeeId;
    private int clientId;
    private int vehicleId;

    public Order() {
    }

    public Order(Date accepted, Date planted, Date start, String descProblem, String descRepair, int status, double repairCost, double partsCost, int hourlyRate, double repairHours, int employeeId, int clientId, int vehicleId) {
        this.accepted = accepted;
        this.planted = planted;
        this.start = start;
        this.descProblem = descProblem;
        this.descRepair = descRepair;
        this.status = status;
        this.repairCost = repairCost;
        this.partsCost = partsCost;
        this.hourlyRate = hourlyRate;
        this.repairHours = repairHours;
        this.employeeId = employeeId;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
    }

    public Order(int id, Date accepted, Date planted, Date start, String descProblem, String descRepair, int status, double repairCost, double partsCost, int hourlyRate, double repairHours, int employeeId, int clientId, int vehicleId) {
        this.id = id;
        this.accepted = accepted;
        this.planted = planted;
        this.start = start;
        this.descProblem = descProblem;
        this.descRepair = descRepair;
        this.status = status;
        this.repairCost = repairCost;
        this.partsCost = partsCost;
        this.hourlyRate = hourlyRate;
        this.repairHours = repairHours;
        this.employeeId = employeeId;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", accepted=" + accepted +
                ", planted=" + planted +
                ", start=" + start +
                ", descProblem='" + descProblem + '\'' +
                ", descRepair='" + descRepair + '\'' +
                ", status=" + status +
                ", repairCost=" + repairCost +
                ", partsCost=" + partsCost +
                ", hourlyRate=" + hourlyRate +
                ", repairHours=" + repairHours +
                ", employeeId=" + employeeId +
                ", clientId=" + clientId +
                ", vehicleId=" + vehicleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAccepted() {
        return accepted;
    }

    public void setAccepted(Date accepted) {
        this.accepted = accepted;
    }

    public Date getPlanted() {
        return planted;
    }

    public void setPlanted(Date planted) {
        this.planted = planted;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getDescProblem() {
        return descProblem;
    }

    public void setDescProblem(String descProblem) {
        this.descProblem = descProblem;
    }

    public String getDescRepair() {
        return descRepair;
    }

    public void setDescRepair(String descRepair) {
        this.descRepair = descRepair;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getRepairHours() {
        return repairHours;
    }

    public void setRepairHours(double repairHours) {
        this.repairHours = repairHours;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
