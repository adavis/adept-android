package info.adavis.adeptandroid.data;

import java.util.ArrayList;
import java.util.List;

import info.adavis.adeptandroid.models.Book;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Annyce Davis
 */
public class BookRepository implements Repository<Book>
{
    @Override
    public void add (final Book item)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction( new Realm.Transaction()
        {
            @Override
            public void execute (Realm realm)
            {
                realm.copyToRealmOrUpdate( item );
            }
        } );
        realm.close();
    }

    @Override
    public void add (final Iterable<Book> items)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction( new Realm.Transaction()
        {
            @Override
            public void execute (Realm realm)
            {
                for ( Book Book : items )
                {
                    realm.copyToRealmOrUpdate( Book );
                }
            }
        } );
        realm.close();
    }

    public List<Book> query ()
    {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Book> realmResults = realm.where( Book.class ).findAll();

        final List<Book> items = new ArrayList<>();

        for ( Book Book : realmResults )
        {
            items.add( realm.copyFromRealm( Book ) );
        }

        realm.close();

        return items;
    }

}
