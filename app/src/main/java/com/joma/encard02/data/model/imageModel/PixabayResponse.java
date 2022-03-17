
package com.joma.encard02.data.model.imageModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PixabayResponse<T> {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("totalHits")
    @Expose
    private Integer totalHits;
    @SerializedName("hits")
    @Expose
    private List<T> hits = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<T> getHits() {
        return hits;
    }

    public void setHits(List<T> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "PixabayResponse{" +
                "total=" + total +
                ", totalHits=" + totalHits +
                ", hits=" + hits +
                '}';
    }
}
