package com.example.capstoneproject.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.capstoneproject.R;
import com.example.capstoneproject.WaniReferenceApplication;
import com.example.capstoneproject.database.entities.LevelEntity;
import com.example.capstoneproject.database.entities.SubjectTypeEntity;
import com.example.capstoneproject.repository.WaniRepository;
import com.example.capstoneproject.ui.MainActivity;
import com.example.capstoneproject.utils.AppExecutors;
import com.example.capstoneproject.utils.SubjectHelper;

import java.util.List;
import java.util.Random;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Implementation of App Widget functionality.
 */
public class SubjectWidget extends AppWidgetProvider {

    private final static CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onUpdate(
            @NonNull final Context context,
            @NonNull final AppWidgetManager appWidgetManager,
            @NonNull final int[] appWidgetIds
    ) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(@NonNull final Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(@NonNull final Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @NonNull
    private static PendingIntent getPendingIntent(@NonNull final Context context) {
        // TODO: Potentially pass in Subject in bundle and deeplink to detail activity of selected subject
        return PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
    }

    private static void updateAppWidget(
            @NonNull final Context context,
            @NonNull final AppWidgetManager appWidgetManager,
            final int appWidgetId
    ) {

        final WaniRepository repository = ((WaniReferenceApplication) context.getApplicationContext()).getAppContainer().getWaniRepository();

        AppExecutors.getInstance().getDiskIO().execute(() -> {
            final List<LevelEntity> levelEntities = repository.loadLocalLevelsSynchronous();

            if (levelEntities.size() != 0) {
                final Random random = new Random();
                final int randomLevel = random.nextInt(levelEntities.size());
                final int randomSubjectType = random.nextInt(3);

                List<SubjectTypeEntity> subjectTypeEntityList;
                switch (randomSubjectType) {
                    case 1:
                        subjectTypeEntityList = levelEntities.get(randomLevel).getKanjiList();
                        break;
                    case 2:
                        subjectTypeEntityList = levelEntities.get(randomLevel).getVocabularyList();
                        break;
                    default:
                        subjectTypeEntityList = levelEntities.get(randomLevel).getRadicalList();
                }

                final int randomSubject = random.nextInt(subjectTypeEntityList.size());

                final SubjectTypeEntity randomSubjectTypeEntity = subjectTypeEntityList.get(randomSubject);

                updateRemoveViews(context, randomSubjectTypeEntity, appWidgetManager, appWidgetId);
            } else {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.subject_widget);
                changeRemoteViewTextAttributes(views, context);
                views.setOnClickPendingIntent(R.id.subject_widget, getPendingIntent(context));
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        });
    }

    private static void updateRemoveViews(
            @NonNull final Context context,
            @NonNull final SubjectTypeEntity subjectTypeEntity,
            @NonNull final AppWidgetManager appWidgetManager,
            final int appWidgetId
    ) {
        CharSequence widgetSubjectMeaning = subjectTypeEntity.getMeaning();
        CharSequence widgetSubjectCharacters = subjectTypeEntity.getCharacters();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.subject_widget);
        final int color = SubjectHelper.getColor(subjectTypeEntity.getSubjectType());
        views.setInt(R.id.subject_widget, "setBackgroundColor", ContextCompat.getColor(context, color));
        views.setTextViewText(R.id.subject_meaning, widgetSubjectMeaning);
        views.setTextViewText(R.id.subject_characters, widgetSubjectCharacters);

        if (subjectTypeEntity.getCharacters().isEmpty()) {
            views.setTextViewText(R.id.subject_characters, context.getString(R.string.unavailable_radical));
            changeRemoteViewTextAttributes(views, context);
        }
        views.setOnClickPendingIntent(R.id.subject_widget, getPendingIntent(context));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static void changeRemoteViewTextAttributes(
            @NonNull final RemoteViews views, @NonNull final Context context
    ) {
        views.setInt(R.id.subject_widget, "setBackgroundColor", ContextCompat.getColor(context, R.color.colorAccent));
        views.setTextViewTextSize(R.id.subject_characters, TypedValue.COMPLEX_UNIT_SP, 24);
        views.setBoolean(R.id.subject_characters, "setSingleLine", false);
    }
}