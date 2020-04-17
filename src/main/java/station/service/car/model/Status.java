package station.service.car.model;

public class Status {
    private int id;
    private int status;
    private int order_id;

    public Status() {
    }

    public Status(int status, int order_id) {
        this.status = status;
        this.order_id = order_id;
    }

    public Status(int id, int status, int order_id) {
        this.id = id;
        this.status = status;
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status=" + status +
                ", order_id=" + order_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
