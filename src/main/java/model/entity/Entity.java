package model.entity;

public interface Entity<ID> {
    ID getId();
    void setId(ID id);
}
