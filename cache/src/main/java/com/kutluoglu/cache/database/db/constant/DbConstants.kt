package com.kutluoglu.cache.database.db.constant

/**
 * Created by F.K. on 2019-06-25.
 *
 */
object DbConstants {
    const val DATABASE_NAME = "PulseLiveDemo.db"
    /**
     * Content Item Table Info
     */
    const val TABLE_NAME_CONTENT = "ContentList"
    const val QUERY_GET_CONTENTS = "SELECT * FROM $TABLE_NAME_CONTENT ORDER BY id Desc"

    /**
     * Content Detail Table Info
     */
    const val TABLE_NAME_CONTENT_DETAIL = "ContentDetail"
    const val QUERY_GET_CONTENT_DETAIL_BY_ID = "SELECT * FROM $TABLE_NAME_CONTENT_DETAIL WHERE contentId = :contentId"
}