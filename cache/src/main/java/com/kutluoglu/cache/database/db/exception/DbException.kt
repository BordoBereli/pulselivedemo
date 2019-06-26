package com.vibuy.legacy.cache.database.db.exception

/**
 * Created by F.K. on 2019-06-25.
 *
 */

class DbException : Exception() {
    override val message: String?
        get() = "There is an error on PulseLive Demo DB operation: " + super.message
}