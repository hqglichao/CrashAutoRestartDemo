package io.github.hqglichao.crashautorestartdemo;

import android.os.RemoteException;

/**
 * @author beyond
 * @date 19-1-4
 */
public class RemoteBinder extends IRemoteBinder.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
