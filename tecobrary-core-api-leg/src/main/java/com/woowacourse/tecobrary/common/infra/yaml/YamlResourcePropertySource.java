package com.woowacourse.tecobrary.common.infra.yaml;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

public class YamlResourcePropertySource extends PropertiesPropertySource {

    public YamlResourcePropertySource(final String name, final EncodedResource resource) throws IOException {
        super(name, new YamlPropertiesProcessor(resource.getResource()).createProperties());
    }
}