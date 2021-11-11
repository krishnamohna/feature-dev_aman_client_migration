package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;

import androidx.loader.content.Loader;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.models.heartrate.HeartRateEntity;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by jboggess on 9/14/16.
 */
public class HeartRateService {

    private final static String HEART_RATE_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d.json";
    private final static String HEART_RATE_LOGS_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/%s/%s.json";
    private static final ResourceLoaderFactory<HeartRateEntity> HEART_RATE_RESOURCE_FACTORY = new ResourceLoaderFactory<>(HEART_RATE_URL, HeartRateEntity.class);
    private static final ResourceLoaderFactory<HeartRateEntity> HEART_RATE_LOGS_RESOURCE_FACTORY = new ResourceLoaderFactory<>(HEART_RATE_LOGS_URL, HeartRateEntity.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<HeartRateEntity>> getHeartRate(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return HEART_RATE_RESOURCE_FACTORY.newResourceLoaderAsync(activityContext, new Scope[]{Scope.heartrate});
    }

    public static ResourceLoaderSync<HeartRateEntity> getHeartRateLogs(Date startDate, Date endDate, int number) throws MissingScopesException, TokenExpiredException {
        /*String periodSuffix = "d";
        switch (calendarDateType) {
            case Calendar.WEEK_OF_YEAR:
                periodSuffix = "w";
                break;
            case Calendar.MONTH:
                periodSuffix = "m";
                break;
        }
        return HEART_RATE_LOGS_RESOURCE_FACTORY.newResourceLoaderSync(
                new Scope[]{Scope.heartrate},
                dateFormat.format(startDate),
                String.format(Locale.US, "%d%s", number, periodSuffix));*/
        return HEART_RATE_LOGS_RESOURCE_FACTORY.newResourceLoaderSync(
                new Scope[]{Scope.heartrate},
                dateFormat.format(startDate),
                dateFormat.format(endDate));
    }

}
