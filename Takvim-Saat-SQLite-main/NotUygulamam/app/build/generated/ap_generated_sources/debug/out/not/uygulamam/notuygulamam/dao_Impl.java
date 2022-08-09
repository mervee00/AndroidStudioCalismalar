package not.uygulamam.notuygulamam;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class dao_Impl implements dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNot;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNot;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfNot;

  public dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNot = new EntityInsertionAdapter<Not>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `notlar`(`id`,`not`,`saat`,`tarih`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Not value) {
        stmt.bindLong(1, value.id);
        if (value.not == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.not);
        }
        if (value.notsaat == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.notsaat);
        }
        if (value.nottarih == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.nottarih);
        }
      }
    };
    this.__deletionAdapterOfNot = new EntityDeletionOrUpdateAdapter<Not>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `notlar` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Not value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfNot = new EntityDeletionOrUpdateAdapter<Not>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `notlar` SET `id` = ?,`not` = ?,`saat` = ?,`tarih` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Not value) {
        stmt.bindLong(1, value.id);
        if (value.not == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.not);
        }
        if (value.notsaat == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.notsaat);
        }
        if (value.nottarih == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.nottarih);
        }
        stmt.bindLong(5, value.id);
      }
    };
  }

  @Override
  public void notEkle(Not not) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNot.insert(not);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void notSil(Not not) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNot.handle(not);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void notGuncelle(Not not) {
    __db.beginTransaction();
    try {
      __updateAdapterOfNot.handle(not);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Not> getNot() {
    final String _sql = "select * from notlar";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfİd = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNot = _cursor.getColumnIndexOrThrow("not");
      final int _cursorIndexOfNotsaat = _cursor.getColumnIndexOrThrow("saat");
      final int _cursorIndexOfNottarih = _cursor.getColumnIndexOrThrow("tarih");
      final List<Not> _result = new ArrayList<Not>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Not _item;
        _item = new Not();
        _item.id = (int) _cursor.getInt(_cursorIndexOfİd);
        _item.not = _cursor.getString(_cursorIndexOfNot);
        _item.notsaat = _cursor.getString(_cursorIndexOfNotsaat);
        _item.nottarih = _cursor.getString(_cursorIndexOfNottarih);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
