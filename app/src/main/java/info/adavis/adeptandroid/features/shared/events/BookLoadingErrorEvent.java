package info.adavis.adeptandroid.features.shared.events;

/**
 * @author Annyce Davis
 */
public class BookLoadingErrorEvent
{
    private long id;

    public BookLoadingErrorEvent (long id)
    {
        this.id = id;
    }

    public long getId ()
    {
        return id;
    }
}
