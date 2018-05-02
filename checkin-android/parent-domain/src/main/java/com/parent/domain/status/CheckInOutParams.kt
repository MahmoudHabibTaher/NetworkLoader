package com.parent.domain.status

import com.parent.domain.base.Params

/**
 * Created by mahmoud on 1/3/18.
 */
object CheckInOutParams {
    const val CHILD_ID = "child_id"
    const val CHILDREN_IDS = "children_ids"
    const val STAFF_IDS = "staff_ids"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val ACCURACY = "accuracy"

    fun buildParams(childrenIds: List<String>, staffIds: List<String>, latitude: Double, longitude: Double) =
            Params(CHILDREN_IDS to childrenIds,
                    STAFF_IDS to staffIds, LATITUDE to latitude,
                    LONGITUDE to longitude)
}