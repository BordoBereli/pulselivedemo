package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.interactor.baseUseCases.SingleUseCase
import com.kutluoglu.domain.model.PulseContentDetail
import com.kutluoglu.domain.repository.PulseLiveRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-24.
 *
 */
open class GetContentDetailUseCase @Inject constructor(
    private val repository: PulseLiveRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<PulseContentDetail, String>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(id: String?): Single<PulseContentDetail> {
        return repository.getContentDetailBy(id)
    }
}