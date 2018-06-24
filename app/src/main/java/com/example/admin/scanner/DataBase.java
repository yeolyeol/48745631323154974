package com.example.admin.scanner;

import android.provider.BaseColumns;

public final class DataBase
{

    public static final class CreateDB implements BaseColumns
    {
        public static final String codeNum = "";
        public static final String howTo = "how";

        public static final String TABLENAME = "data";
        public static final String CREATE = "create table "
                +TABLENAME+ "("
                +_ID+ " integer primary key autoincrement, "
                +codeNum+ "text not null, "
                +howTo+ "text not nul);";
    }
}