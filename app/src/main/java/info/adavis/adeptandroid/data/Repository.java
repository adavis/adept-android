package info.adavis.adeptandroid.data;

/**
 * @author Annyce Davis
 */
public interface Repository<T>
{
    void add (final T item);

    void add (final Iterable<T> items);

}
