package com.loftschool.pivovarov.loftmoney1;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceFragment extends Fragment {

    private DiagramView mDiagraView;
    private TextView mTotalMoney;
    private TextView mExpenseMoney;
    private TextView mIncomeMoney;
    private Api mApi;

    public static BalanceFragment newInstance() {
        BalanceFragment fragment = new BalanceFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View fragmentView = inflater.inflate(R.layout.fragment_balance, container, false);

        mDiagraView = fragmentView.findViewById(R.id.diagramView);
        mTotalMoney = fragmentView.findViewById(R.id.total_money);
        mExpenseMoney = fragmentView.findViewById(R.id.expense_money);
        mIncomeMoney = fragmentView.findViewById(R.id.income_money);

        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApi = ((LoftApp) getActivity().getApplication()).getApi();
    }

    @Override
    public void onStart() {
        super.onStart();

        loadBalance();

    }

    private void loadBalance() {
        final String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("auth_token", "");
        Call<BalanceResponse> balanceResponseCall = mApi.getBalance(token);
        balanceResponseCall.enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(
                    final Call<BalanceResponse> call, final Response<BalanceResponse> response
            ) {
               mTotalMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalIncome() - response.body().getTotalExpence())));
               mExpenseMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalExpence())));
               mIncomeMoney.setText(getString(R.string.price_template, String.valueOf(response.body().getTotalIncome())));

               mDiagraView.update(response.body().getTotalIncome(), response.body().getTotalExpence());


            }

            @Override
            public void onFailure(final Call<BalanceResponse> call, final Throwable t) {

            }
        });
    }
}
