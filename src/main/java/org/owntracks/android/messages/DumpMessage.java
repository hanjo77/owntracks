package org.owntracks.android.messages;

import android.util.Log;

import org.json.JSONException;

import org.json.JSONObject;
import org.owntracks.android.model.GeocodableLocation;
import org.owntracks.android.services.ServiceBroker;
import org.owntracks.android.services.ServiceLocator;

public class DumpMessage extends Message {
    private LocationMessage location;
    private ConfigurationMessage configuration;
    private boolean locatorReady;
    private boolean locatorForeground;
    private GeocodableLocation locatorLastKnownLocation;
    private Long locatorLastPublishDate;
    private Integer locatorWaypointCount;
    private boolean locatorHasLocationClient;
    private boolean locatorHasLocationRequest;
    private ServiceLocator.State locatorState;
    private Short brokerKeepAliveSeconds;
    private Exception brokerError;
    private Integer brokerDeferredPublishablesCount;
    private ServiceBroker.State brokerState;
    private boolean applicationPlayServicesAvailable;
    private JSONObject locatorDebug;

    public void setLocation(LocationMessage location) {
        this.location = location;
    }

    public void setConfiguration(ConfigurationMessage configuration) {
        this.configuration = configuration;
    }

    public void setLocatorReady(boolean locatorReady) {
        this.locatorReady = locatorReady;
    }

    public void setLocatorForeground(boolean locatorForeground) {
        this.locatorForeground = locatorForeground;
    }

    public void setLocatorLastKnownLocation(GeocodableLocation locatorLastKnownLocation) {
        this.locatorLastKnownLocation = locatorLastKnownLocation;
    }

    public void setLocatorLastPublishDate(Long locatorLastPublishDate) {
        this.locatorLastPublishDate = locatorLastPublishDate;
    }

    public void setLocatorWaypointCount(Integer locatorWaypointCount) {
        this.locatorWaypointCount = locatorWaypointCount;
    }

    public void setLocatorHasLocationClient(boolean locatorHasLocationClient) {
        this.locatorHasLocationClient = locatorHasLocationClient;
    }

    public void setLocatorHasLocationRequest(boolean locatorHasLocationRequest) {
        this.locatorHasLocationRequest = locatorHasLocationRequest;
    }

    public void setBrokerKeepAliveSeconds(Short brokerKeepAliveSeconds) {
        this.brokerKeepAliveSeconds = brokerKeepAliveSeconds;
    }

    public void setBrokerError(Exception brokerError) {
        this.brokerError = brokerError;
    }
    public void setBrokerDeferredPublishablesCount(Integer deferedPublishablesCount) {
        this.brokerDeferredPublishablesCount = deferedPublishablesCount;
    }

    public void setApplicationPlayServicesAvailable(boolean applicationPlayServicesAvailable) {
        this.applicationPlayServicesAvailable = applicationPlayServicesAvailable;
    }

    public void setLocatorState(ServiceLocator.State locatorState) {
        this.locatorState = locatorState;
    }

    public void setBrokerState(ServiceBroker.State brokerState) {
        this.brokerState = brokerState;
    }

    public String toString() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("_type", "dump")
                .put("systemDate", new java.util.Date())
                .put("location", location.toJSONObject())
                .put("configuration", configuration.toJSONObject())
                .put("internal", new JSONObject()

                                .put("broker", new JSONObject()
                                                .put("keepAliveSeconds", brokerKeepAliveSeconds)
                                                .put("error", brokerError != null ? brokerError.toString() : null)
                                                .put("deferredPublishablesCount", brokerDeferredPublishablesCount)
                                                .put("state", brokerState)
                                )
                                .put("application", new JSONObject()
                                                .put("playServicesAvailable", applicationPlayServicesAvailable)
                                )
                                .put("locator", new JSONObject()
                                                .put("ready", locatorReady)
                                                .put("foreground", locatorForeground)
                                                .put("lastKnownLocation", locatorLastKnownLocation != null ? locatorLastKnownLocation : null)
                                                .put("lastPublishDate", locatorLastPublishDate != null ? locatorLastPublishDate : null)
                                                .put("waypointCount", locatorWaypointCount)
                                                .put("hasLocationClient", locatorHasLocationClient)
                                                .put("hasLocationRequest", locatorHasLocationRequest)
                                                .put("serviceCreationDate", locatorState)
                                                .put("state", locatorState)
                                                .put("debug", locatorDebug)
                                )
                );


        } catch (JSONException e) {
            Log.e(this.toString(), e.toString());

        }
        return json;
    }

    public void setLocatorDebug(JSONObject locatorDebug) {
        this.locatorDebug = locatorDebug;
    }

    public JSONObject getLocatorDebug() {
        return locatorDebug;
    }
}
