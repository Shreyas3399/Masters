public interface StoreEverythingI<T> {
    public boolean delete(T o);
    public boolean find(T o);
    public boolean add(T o);
    public String toString();
}
