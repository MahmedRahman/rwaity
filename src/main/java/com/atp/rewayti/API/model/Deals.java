
package com.atp.rewayti.API.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Deals {

    @SerializedName("deals")
    private List<Deal> mDeals;

    public List<Deal> getDeals() {
        return mDeals;
    }

    public void setDeals(List<Deal> deals) {
        mDeals = deals;
    }

}
