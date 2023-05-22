abstract class Storage {
    int counterNull=0;
    public boolean sortedState=true;
    public int howManyNulls()
    {
        return counterNull;
    }
    public abstract boolean isSorted();
    public abstract boolean find(Object x);
    public abstract boolean add(Object x);
    public abstract boolean delete(Object x);

    public abstract String toString();
}
