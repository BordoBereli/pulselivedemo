package com.kutluoglu.domain.repository

import com.kutluoglu.domain.model.PulseContent
import com.kutluoglu.domain.model.PulseContentDetail
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-24.
 *
 */

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */

interface PulseLiveRepository {
    /**
     * Get List of [PulseContent]
     */
    fun getContentList() : Single<List<PulseContent>>

    /**
     * Get [PulseContentDetail] by its id.
     */
    fun getContentDetailBy(id: String?) : Single<PulseContentDetail>
}