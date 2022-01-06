package com.cardio.physician.data.remote.fitnesstracker.googlefit;

import android.util.Log;
import com.cardio.physician.data.remote.fitnesstracker.common.DataParser;
import com.cardio.physician.domain.fitness.model.*;
import com.cardio.physician.ui.common.utils.GoogleFit;
import com.google.android.gms.fitness.data.Bucket;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a8\u0006\u000e"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/DataParserGoogleFit;", "Lcom/cardio/physician/data/remote/fitnesstracker/common/DataParser;", "()V", "parseLogsBucket", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "buckets", "", "Lcom/google/android/gms/fitness/data/Bucket;", "periodDays", "", "parseSingleData", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "listBuckets", "", "app_qaDebug"})
public final class DataParserGoogleFit extends com.cardio.physician.data.remote.fitnesstracker.common.DataParser {
    
    public DataParserGoogleFit() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.SyncModel parseLogsBucket(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.google.android.gms.fitness.data.Bucket> buckets, int periodDays) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.FitnessModel parseSingleData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.fitness.data.Bucket> listBuckets) {
        return null;
    }
}