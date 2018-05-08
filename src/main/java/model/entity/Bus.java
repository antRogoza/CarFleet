package model.entity;

import model.entity.proxy.BusProxy;

import java.util.List;
import java.util.Objects;

public class Bus implements Entity<Long> {
    private Long id;
    private String brand;
    private int numberOfSeats;
    private List<User> users;
    private List<Route> routes;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routs) {
        this.routes = routs;
    }

    public static final class BusBuilder {
        private Long id;
        private String brand;
        private int numberOfSeats;
        private List<User> users;
        private List<Route> routes;

        public BusBuilder setId(Long id){
            this.id = id;
            return this;
        }

        public BusBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public BusBuilder setNumberOfSeats(int numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
            return this;
        }

        public BusBuilder setUsers(List<User> users){
            this.users = users;
            return this;
        }

        public BusBuilder setRouts(List<Route> routs){
            this.routes = routs;
            return this;
        }

        public Bus buildBus() {
            Bus bus = new Bus();
            bus.setId(id);
            bus.setBrand(brand);
            bus.setNumberOfSeats(numberOfSeats);
            bus.setUsers(users);
            bus.setRoutes(routes);
            return bus;
        }

        public Bus buildBusProxy() {
            BusProxy bus = new BusProxy();
            bus.setId(id);
            bus.setBrand(brand);
            bus.setNumberOfSeats(numberOfSeats);
            bus.setUsers(users);
            bus.setRoutes(routes);
            return bus;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return numberOfSeats == bus.numberOfSeats &&
                Objects.equals(id, bus.id) &&
                Objects.equals(brand, bus.brand) &&
                Objects.equals(users, bus.users) &&
                Objects.equals(routes, bus.routes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, brand, numberOfSeats, users, routes);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", users=" + users +
                ", routes=" + routes +
                '}';
    }
}
