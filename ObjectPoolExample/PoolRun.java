package ObjectPoolExample;

public class PoolRun {

    public static void main(String[] args) {
        WorkerPool reusablePool = new WorkerPool(1, 5);
        Worker worker1 = reusablePool.getInstance();
        Worker worker2 = reusablePool.getInstance();
        Worker worker3 = reusablePool.getInstance();
        Worker worker4 = reusablePool.getInstance();
        Worker worker5 = reusablePool.getInstance();

        reusablePool.releaseObject(worker1);

        System.out.println("Total workers in use: " + reusablePool.inUse.size());
        System.out.println("Total workers available in pool: " + reusablePool.available.size());

        Worker worker6 = reusablePool.getInstance();
        System.out.println("Worker 6 details: " + worker6.id + " " + worker6.name + " " + worker6.country);

        System.out.println("Total workers in use: " + reusablePool.inUse.size());
        System.out.println("Total workers available in pool: " + reusablePool.available.size());
    }

}
