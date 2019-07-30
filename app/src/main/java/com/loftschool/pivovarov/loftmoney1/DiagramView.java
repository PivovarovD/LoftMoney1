package com.loftschool.pivovarov.loftmoney1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class DiagramView extends View {


    private Paint expensePaint = new Paint();
    private Paint incomePaint = new Paint();

    private int expense;
    private int income;

    public DiagramView(
            Context context) {
        super(context);
        init (context, null);
    }

    public DiagramView(
            final Context context,
            final AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public DiagramView(
            final Context context, final AttributeSet attrs,
            final int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public DiagramView(
            final Context context,
            final AttributeSet attrs,
            final int defStyleAttr,
            final int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){
        if (attrs != null) {
            expensePaint.setColor(ContextCompat.getColor(context, R.color.dark_sky_blue));
            incomePaint.setColor(ContextCompat.getColor(context, R.color.apple_green));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void update(int income, int expense) {
        this.income = income;
        this.expense = expense;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int total = expense + income;

        if (total == 0) {
            return;
        }

        float expenseAngle = 360f * expense / total;
        float incomeAngle = 360f * income / total;

        int space = 10;
        int size = Math.min(getWidth(), getHeight()) - space * 2;
        int xMargin = (getWidth() - size) / 2;
        int yMargin = (getHeight() -size) / 2;

        canvas.drawArc(xMargin - space, yMargin, getWidth() - xMargin - space, getHeight() - yMargin,
                 180 -expenseAngle / 2, expenseAngle, true, expensePaint);

        canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space, getHeight() - yMargin,
                360 - incomeAngle / 2, incomeAngle, true, incomePaint);


    }


}
