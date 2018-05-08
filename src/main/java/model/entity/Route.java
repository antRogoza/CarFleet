package model.entity;

import model.entity.proxy.RoleProxy;
import model.entity.proxy.RouteProxy;

import java.util.List;
import java.util.Objects;

public class Route implements Entity<Long>{
    private Long id;
    private String name;
    private String startPoint;
    private String endPoint;
    private List<Bus> buses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public static final class RouteBuilder {
        private Long id;
        private String name;
        private String startPoint;
        private String endPoint;
        private List<Bus> buses;

        public RouteBuilder setId(Long id){
            this.id = id;
            return this;
        }

        public RouteBuilder setName(String name){
            this.name = name;
            return this;
        }

        public RouteBuilder setStartPoint(String startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public RouteBuilder setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public RouteBuilder setBuses(List<Bus> buses){
            this.buses = buses;
            return this;
        }

        public Route buildRoute() {
            Route route = new Route();
            route.setId(id);
            route.setStartPoint(startPoint);
            route.setEndPoint(endPoint);
            route.setBuses(buses);
            return route;
        }

        public Route buildRouteProxy() {
            RouteProxy route = new RouteProxy();
            route.setId(id);
            route.setStartPoint(startPoint);
            route.setEndPoint(endPoint);
            route.setBuses(buses);
            return route;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(name, route.name) &&
                Objects.equals(startPoint, route.startPoint) &&
                Objects.equals(endPoint, route.endPoint) &&
                Objects.equals(buses, route.buses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, startPoint, endPoint, buses);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", buses=" + buses +
                '}';
    }
}
