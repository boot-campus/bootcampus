package com.boot.campus.common.config;

import com.boot.campus.member.domain.MemberType;
import org.springframework.core.convert.converter.Converter;

public class MemberTypeConverter implements Converter<String, MemberType> {
    
    @Override
    public MemberType convert(final String source) {
        return MemberType.fromName(source);
    }
}
