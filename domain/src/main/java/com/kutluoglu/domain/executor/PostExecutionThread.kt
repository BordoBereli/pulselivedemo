package com.kutluoglu.domain.executor

import io.reactivex.Scheduler

/**
 * Created by F.K. on 2019-06-24.
 *
 */

interface PostExecutionThread {
    val scheduler: Scheduler
}