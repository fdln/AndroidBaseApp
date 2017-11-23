package com.projectname.model.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Created by Fadhlan on 11/23/17.
 */

@AutoValue
@JsonDeserialize (builder = AutoValue_ImageInfo.Builder.class)
public abstract class ImageInfo {
    @JsonProperty ("image_name") public abstract String imageName();
    @JsonProperty ("url") public abstract String imageUrl();

    public static ImageInfo create(String imageName, String imageUrl) {
        return builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .build();
    }

    public static Builder builder() {return new AutoValue_ImageInfo.Builder();}

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder imageName(String imageName);
        public abstract Builder imageUrl(String imageUrl);
        public abstract ImageInfo build();
    }
}
