package ObjectPoolExample;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ReusablePool<T> {

    private long expirationTime;
    protected Hashtable<T, Long> available;
    protected Hashtable<T, Long> inUse;

    public ReusablePool(int contractTime) {
        expirationTime = contractTime;
        available = new Hashtable<T, Long>();
        inUse = new Hashtable<T, Long>();
    }

    protected abstract T createObject();

    public abstract void expiredObject(T o);

    public abstract boolean validateObject(T o);

    public synchronized T getInstance() {
        long now = System.currentTimeMillis();
        T t;
        if(available.size() > 0) {
            Enumeration<T> e = available.keys();
            while(e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - available.get(t)) > expirationTime) {
                    // object has expired
                    available.remove(t);
                    expiredObject(t);
                    t = null;
                } else {
                    if (validateObject(t)) {
                        available.remove(t);
                        inUse.put(t, now);
                        return (t);
                    } else {
                        // object failed validation
                        available.remove(t);
                        expiredObject(t);
                        t = null;
                    }
                }
            }
        }
        t = createObject();
        inUse.put(t, now);
        return t;
    }

    public synchronized void releaseObject(T t) {
        inUse.remove(t);
        available.put(t, System.currentTimeMillis());
    }
}
