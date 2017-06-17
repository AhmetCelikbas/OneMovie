package com.lpsmin.onemovie.model;

import com.lpsmin.onemovie.enumerations.AppendToResponseItem;

/**
 * Created by younes on 17/06/2017.
 */

public class AppendToResponse {

    private final AppendToResponseItem[] items;

    public AppendToResponse(AppendToResponseItem... items) {
        this.items = items;
    }

    @Override
    public String toString() {
        if (items != null && items.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.length ; i++) {
                sb.append(items[i]);

                if (i < items.length - 1) {
                    sb.append(',');
                }
            }

            return sb.toString();
        }

        return null;
    }

}
