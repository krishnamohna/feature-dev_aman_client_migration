package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;

import androidx.loader.content.Loader;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.DailyActivitySummary;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jboggess on 10/3/16.
 */
public class ActivityService {

    private final static String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities/date/%s.json";
    private final static String ACTIVITIES_LOGS_URL = "https://api.fitbit.com/1/user/-/activities/steps/date/%s/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_ACTIVITIES_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_URL, DailyActivitySummary.class);
    private static final ResourceLoaderFactory<StepCountEntity> USER_ACTIVITIES_LOGS_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_LOGS_URL, StepCountEntity.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getDailyActivitySummaryLoader(Activity activityContext, Date date) throws MissingScopesException, TokenExpiredException {
        return USER_ACTIVITIES_LOADER_FACTORY.newResourceLoaderAsync(activityContext, new Scope[]{Scope.activity}, dateFormat.format(date));
    }

    public static ResourceLoaderSync<StepCountEntity> getStepLogs(Date startDate, Date endDate, int number) throws MissingScopesException, TokenExpiredException {
        return USER_ACTIVITIES_LOGS_LOADER_FACTORY.newResourceLoaderSync(
                new Scope[]{Scope.heartrate},
                dateFormat.format(startDate),
                dateFormat.format(endDate));
    }
}
