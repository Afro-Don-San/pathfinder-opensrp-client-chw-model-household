package com.adosa.opensrp.chw.household.contract;

import android.content.Context;

public interface BaseFpCallDialogContract {
    interface View {

        Dialer getPendingCallRequest();

        void setPendingCallRequest(Dialer dialer);

        Context getCurrentContext();
    }

    interface Dialer {
        void callMe();
    }
}
