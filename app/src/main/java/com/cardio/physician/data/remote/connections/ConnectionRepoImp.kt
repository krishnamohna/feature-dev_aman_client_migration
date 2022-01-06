package com.cardio.physician.data.remote.connections

import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.network.NetworkError
import com.cardio.physician.ui.common.utils.Constants
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.toConnectionModel
import com.cardio.physician.ui.common.utils.getPatientUid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConnectionRepoImp @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
) :
    ConnectionRepo {

    override suspend fun getMyConnections(): List<ConnectionModel> {
        val query: Query = fireStore.collection(FireStoreCollection.CONNECTIONS)
            .document(Constants.USER_TYPE_PHYSICIAN)
            .collection(getPatientUid())
            .whereEqualTo(FireStoreDocKey.REQUEST_STATUS,true)
        return query.get().await().run {
            if(isEmpty)
                throw NetworkError.noRecordFound()
            this.toConnectionModel()
        }
    }
}
