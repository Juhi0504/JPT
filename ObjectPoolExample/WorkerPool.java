package ObjectPoolExample;

public class WorkerPool extends ReusablePool<Worker>{

    int id;

    public WorkerPool(int startingId, int contractTime) {
        super(contractTime);
        id = startingId;
    }

    @Override
    protected Worker createObject() {
        Worker worker= new Worker(id, "Worker" + id, "India");
        id++;
        return worker;
    }

    @Override
    public void expiredObject(Worker o) {
        System.out.println(o.name + " is removed");
    }

    @Override
    public boolean validateObject(Worker o) {
        if(o.country.equals("India")) {
            return true;
        }
        return false;
    }
}
