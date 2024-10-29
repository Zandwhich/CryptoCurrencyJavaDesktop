package com.company.api_call.endpoint_data;

import java.time.LocalDateTime;

final public class EndpointData implements EndpointDataInterface{

    private double price = -1;

    private LocalDateTime lastSuccessfulUpdate = null;

    private boolean isUpdating = false;

    private boolean wasLastUpdateSuccessful = false;


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public LocalDateTime getLastSuccessfulUpdate() {
        return this.lastSuccessfulUpdate;
    }

    @Override
    public void setLastSuccessfulUpdate(final LocalDateTime lastSuccessfulUpdate) {
        this.lastSuccessfulUpdate = lastSuccessfulUpdate;
    }

    @Override
    public boolean isUpdating() {
        return this.isUpdating;
    }

    @Override
    public void setUpdating(final boolean updating) {
        this.isUpdating = updating;
    }

    @Override
    public boolean wasLastUpdateSuccessful() {
        return this.wasLastUpdateSuccessful;
    }

    @Override
    public void setWasLastUpdateSuccessful(final boolean wasLastUpdateSuccessful) {
        this.wasLastUpdateSuccessful = wasLastUpdateSuccessful;
    }
}
