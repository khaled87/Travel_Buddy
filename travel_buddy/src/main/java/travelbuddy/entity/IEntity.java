package travelbuddy.entity;

import java.io.Serializable;

public interface IEntity<K> extends Serializable  {
    public K getId();
}
