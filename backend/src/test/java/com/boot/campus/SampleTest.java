package com.boot.campus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class SampleTest {

    @Test
    void 성공_테스트() {
        // given
        boolean flag = true;

        // when

        // then
        assertThat(flag).isTrue();
    }

    @Test
    void 실패_테스트() {
        // given
        boolean flag = false;

        // when

        // then
        assertThat(flag).isTrue();
    }
}
