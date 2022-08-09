package not.uygulamam.notuygulamam;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class veritabani_Impl extends veritabani {
  private volatile dao _dao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `notlar` (`id` INTEGER NOT NULL, `not` TEXT, `saat` TEXT, `tarih` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c3777d7255c9d85a854564df0d6f039f\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `notlar`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsNotlar = new HashMap<String, TableInfo.Column>(4);
        _columnsNotlar.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsNotlar.put("not", new TableInfo.Column("not", "TEXT", false, 0));
        _columnsNotlar.put("saat", new TableInfo.Column("saat", "TEXT", false, 0));
        _columnsNotlar.put("tarih", new TableInfo.Column("tarih", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotlar = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotlar = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotlar = new TableInfo("notlar", _columnsNotlar, _foreignKeysNotlar, _indicesNotlar);
        final TableInfo _existingNotlar = TableInfo.read(_db, "notlar");
        if (! _infoNotlar.equals(_existingNotlar)) {
          throw new IllegalStateException("Migration didn't properly handle notlar(not.uygulamam.notuygulamam.Not).\n"
                  + " Expected:\n" + _infoNotlar + "\n"
                  + " Found:\n" + _existingNotlar);
        }
      }
    }, "c3777d7255c9d85a854564df0d6f039f", "f88d0190a9a6cd512668b73d225a3f7e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "notlar");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `notlar`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public dao dao() {
    if (_dao != null) {
      return _dao;
    } else {
      synchronized(this) {
        if(_dao == null) {
          _dao = new dao_Impl(this);
        }
        return _dao;
      }
    }
  }
}
