package com.loftschool.pivovarov.loftmoney1;

public enum FragmentType {

    expense(R.color.dark_sky_blue),
    income(R.color.income_price_color);

    FragmentType(final int priceColor) {
        this.priceColor = priceColor;
    }

    private int priceColor;

    public int getPriceColor() {
        return priceColor;
    }
}
