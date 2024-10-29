package com.company.api_call.endpoint_data;

import java.time.LocalDateTime;

public interface EndpointDataInterface {

    double getPrice();

    void setPrice(final double price);

    LocalDateTime getLastSuccessfulUpdate();

    void setLastSuccessfulUpdate(final LocalDateTime lastSuccessfulUpdate);

    boolean isUpdating();

    void setUpdating(final boolean updating);

    boolean wasLastUpdateSuccessful();

    void setWasLastUpdateSuccessful(final boolean wasLastUpdateSuccessful);

}
