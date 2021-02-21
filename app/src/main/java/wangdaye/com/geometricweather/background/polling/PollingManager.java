package wangdaye.com.geometricweather.background.polling;

import android.content.Context;
import android.os.Build;

import wangdaye.com.geometricweather.background.polling.services.permanent.PermanentServiceHelper;
import wangdaye.com.geometricweather.background.polling.work.WorkerHelper;
import wangdaye.com.geometricweather.settings.SettingsOptionManager;
import wangdaye.com.geometricweather.common.utils.helpers.IntentHelper;

/**
 * Polling manager.
 * */
public class PollingManager {

    public static void resetAllBackgroundTask(Context context, boolean forceRefresh) {
        if (forceRefresh) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                IntentHelper.startAwakeForegroundUpdateService(context);
                return;
            } else {
                WorkerHelper.setExpeditedPollingWork(context);
            }
        }

        SettingsOptionManager settings = SettingsOptionManager.getInstance(context);
        if (settings.isBackgroundFree()) {
            PermanentServiceHelper.stopPollingService(context);

            WorkerHelper.setNormalPollingWork(
                    context,
                    SettingsOptionManager.getInstance(context).getUpdateInterval().getIntervalInHour());

            if (settings.isTodayForecastEnabled()) {
                WorkerHelper.setTodayForecastUpdateWork(context, settings.getTodayForecastTime(), false);
            } else {
                WorkerHelper.cancelTodayForecastUpdateWork(context);
            }

            if (settings.isTomorrowForecastEnabled()) {
                WorkerHelper.setTomorrowForecastUpdateWork(context, settings.getTomorrowForecastTime(), false);
            } else {
                WorkerHelper.cancelTomorrowForecastUpdateWork(context);
            }
        } else {
            WorkerHelper.cancelNormalPollingWork(context);
            WorkerHelper.cancelTodayForecastUpdateWork(context);
            WorkerHelper.cancelTomorrowForecastUpdateWork(context);

            PermanentServiceHelper.startPollingService(context);
        }
    }

    public static void resetNormalBackgroundTask(Context context, boolean forceRefresh) {
        if (forceRefresh) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                IntentHelper.startAwakeForegroundUpdateService(context);
                return;
            } else {
                WorkerHelper.setExpeditedPollingWork(context);
            }
        }

        if (SettingsOptionManager.getInstance(context).isBackgroundFree()) {
            PermanentServiceHelper.stopPollingService(context);

            WorkerHelper.setNormalPollingWork(
                    context,
                    SettingsOptionManager.getInstance(context).getUpdateInterval().getIntervalInHour());
        } else {
            WorkerHelper.cancelNormalPollingWork(context);
            WorkerHelper.cancelTodayForecastUpdateWork(context);
            WorkerHelper.cancelTomorrowForecastUpdateWork(context);

            PermanentServiceHelper.startPollingService(context);
        }
    }

    public static void resetTodayForecastBackgroundTask(Context context, boolean forceRefresh,
                                                        boolean nextDay) {
        if (forceRefresh) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                IntentHelper.startAwakeForegroundUpdateService(context);
                return;
            } else {
                WorkerHelper.setExpeditedPollingWork(context);
            }
        }

        SettingsOptionManager settings = SettingsOptionManager.getInstance(context);
        if (settings.isBackgroundFree()) {
            PermanentServiceHelper.stopPollingService(context);

            if (settings.isTodayForecastEnabled()) {
                WorkerHelper.setTodayForecastUpdateWork(context, settings.getTodayForecastTime(), nextDay);
            } else {
                WorkerHelper.cancelTodayForecastUpdateWork(context);
            }
        } else {
            WorkerHelper.cancelNormalPollingWork(context);
            WorkerHelper.cancelTodayForecastUpdateWork(context);
            WorkerHelper.cancelTomorrowForecastUpdateWork(context);

            PermanentServiceHelper.startPollingService(context);
        }
    }

    public static void resetTomorrowForecastBackgroundTask(Context context, boolean forceRefresh,
                                                           boolean nextDay) {
        if (forceRefresh) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                IntentHelper.startAwakeForegroundUpdateService(context);
                return;
            } else {
                WorkerHelper.setExpeditedPollingWork(context);
            }
        }

        SettingsOptionManager settings = SettingsOptionManager.getInstance(context);
        if (settings.isBackgroundFree()) {
            PermanentServiceHelper.stopPollingService(context);

            if (settings.isTomorrowForecastEnabled()) {
                WorkerHelper.setTomorrowForecastUpdateWork(context, settings.getTomorrowForecastTime(), nextDay);
            } else {
                WorkerHelper.cancelTomorrowForecastUpdateWork(context);
            }
        } else {
            WorkerHelper.cancelNormalPollingWork(context);
            WorkerHelper.cancelTodayForecastUpdateWork(context);
            WorkerHelper.cancelTomorrowForecastUpdateWork(context);

            PermanentServiceHelper.startPollingService(context);
        }
    }
}
