package com.oguzbabaoglu.kayakairlines.util;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineComponent;
import com.oguzbabaoglu.kayakairlines.features.airlines.DaggerAirlineComponent;

/**
 * Initiates the main component on first injection call. Thread-safe.
 */
public enum Dagger {
  INJECTOR;

  private final AirlineComponent airlineComponent;

  Dagger() {
    this.airlineComponent = DaggerAirlineComponent.builder()
        .contextModule(new ContextModule(KayakApplication.instance))
        .build();
  }

  public AirlineComponent airlineComponent() {
    return airlineComponent;
  }
}
