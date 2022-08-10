package com.example.androidherexamen.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Database_Impl extends Database {
  private volatile PostDatabaseDAO _postDatabaseDAO;

  private volatile CommentDatabaseDAO _commentDatabaseDAO;

  private volatile UserDatabaseDAO _userDatabaseDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Post` (`postId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `text` TEXT NOT NULL, `photo` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Comment` (`commentId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `postId` INTEGER NOT NULL, `text` TEXT NOT NULL, `isSubComment` INTEGER NOT NULL, `subCommentId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`userId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `role` TEXT NOT NULL, `avatar` TEXT NOT NULL, `profielfoto` TEXT NOT NULL, `begeleiderId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fafc6c43e57fb7f455407961c900918e')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Post`");
        _db.execSQL("DROP TABLE IF EXISTS `Comment`");
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
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
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPost = new HashMap<String, TableInfo.Column>(4);
        _columnsPost.put("postId", new TableInfo.Column("postId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPost.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPost.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPost.put("photo", new TableInfo.Column("photo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPost = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPost = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPost = new TableInfo("Post", _columnsPost, _foreignKeysPost, _indicesPost);
        final TableInfo _existingPost = TableInfo.read(_db, "Post");
        if (! _infoPost.equals(_existingPost)) {
          return new RoomOpenHelper.ValidationResult(false, "Post(com.example.androidherexamen.database.Post).\n"
                  + " Expected:\n" + _infoPost + "\n"
                  + " Found:\n" + _existingPost);
        }
        final HashMap<String, TableInfo.Column> _columnsComment = new HashMap<String, TableInfo.Column>(6);
        _columnsComment.put("commentId", new TableInfo.Column("commentId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComment.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComment.put("postId", new TableInfo.Column("postId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComment.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComment.put("isSubComment", new TableInfo.Column("isSubComment", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComment.put("subCommentId", new TableInfo.Column("subCommentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComment = new TableInfo("Comment", _columnsComment, _foreignKeysComment, _indicesComment);
        final TableInfo _existingComment = TableInfo.read(_db, "Comment");
        if (! _infoComment.equals(_existingComment)) {
          return new RoomOpenHelper.ValidationResult(false, "Comment(com.example.androidherexamen.database.Comment).\n"
                  + " Expected:\n" + _infoComment + "\n"
                  + " Found:\n" + _existingComment);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(5);
        _columnsUser.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("avatar", new TableInfo.Column("avatar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("profielfoto", new TableInfo.Column("profielfoto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("begeleiderId", new TableInfo.Column("begeleiderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.androidherexamen.database.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fafc6c43e57fb7f455407961c900918e", "3e34ea62de6d60e41ed24b9354b79ee1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Post","Comment","User");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Post`");
      _db.execSQL("DELETE FROM `Comment`");
      _db.execSQL("DELETE FROM `User`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PostDatabaseDAO.class, PostDatabaseDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CommentDatabaseDAO.class, CommentDatabaseDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDatabaseDAO.class, UserDatabaseDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public PostDatabaseDAO getPostDatabaseDAO() {
    if (_postDatabaseDAO != null) {
      return _postDatabaseDAO;
    } else {
      synchronized(this) {
        if(_postDatabaseDAO == null) {
          _postDatabaseDAO = new PostDatabaseDAO_Impl(this);
        }
        return _postDatabaseDAO;
      }
    }
  }

  @Override
  public CommentDatabaseDAO getCommentDatabaseDAO() {
    if (_commentDatabaseDAO != null) {
      return _commentDatabaseDAO;
    } else {
      synchronized(this) {
        if(_commentDatabaseDAO == null) {
          _commentDatabaseDAO = new CommentDatabaseDAO_Impl(this);
        }
        return _commentDatabaseDAO;
      }
    }
  }

  @Override
  public UserDatabaseDAO getUserDatabaseDAO() {
    if (_userDatabaseDAO != null) {
      return _userDatabaseDAO;
    } else {
      synchronized(this) {
        if(_userDatabaseDAO == null) {
          _userDatabaseDAO = new UserDatabaseDAO_Impl(this);
        }
        return _userDatabaseDAO;
      }
    }
  }
}
