package com.loftschool.pivovarov.loftmoney1;

import com.google.gson.annotations.SerializedName;

public class BalanceResponse {

    @SerializedName("total_income")
    private int totalIncome;

    @SerializedName("total_expenses")
    private int totalExpence;

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getTotalExpence() {
        return totalExpence;
    }

    public void setTotalExpence(int totalExpence) {
        this.totalExpence = totalExpence;
    }
}
