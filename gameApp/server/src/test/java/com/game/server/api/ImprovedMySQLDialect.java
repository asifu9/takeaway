package com.game.server.api;

import org.hibernate.dialect.HSQLDialect;

public class ImprovedMySQLDialect extends HSQLDialect {
    @Override
    public String getDropSequenceString(String sequenceName) {
        return "drop sequence if exists " + sequenceName;
    }

    @Override
    public boolean dropConstraints() {
        // We don't need to drop constraints before dropping tables, that just leads to error
        // messages about missing tables when we don't have a schema in the database
        return false;
    }
    
    @Override
    public String getAlterTableString(String tableName) {
    	return super.getAlterTableString(tableName);
    }
    
    @Override
    public String getDropForeignKeyString() {
    	return super.getDropForeignKeyString();
    }
}
