package com.boot.campus.auth.presentation;

import com.boot.campus.auth.infrastructure.github.GitHubApiClient;
import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.common.RedisTestContainer;
import com.boot.campus.common.exception.dto.ExceptionResponse;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberIdentity;
import com.boot.campus.member.domain.MemberType;
import com.boot.campus.member.persistence.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;

import static com.boot.campus.auth.exception.AuthExceptionType.INVALID_OAUTH_API_REQUEST;
import static com.boot.campus.common.exception.CommonExceptionType.INVALID_REQUEST;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthControllerTest extends RedisTestContainer {
    
    @LocalServerPort
    private int port;
    
    @MockBean
    private GitHubApiClient gitHubApiClient;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"GITHUB"})
    void 존재하지_않는_멤버라면_새로_멤버로_등록하고_로그인에_성공한다(String memberType) {
        //given
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenReturn(new GitHubToken("accessToken", "scope", "tokenType"));
        Mockito.when(gitHubApiClient.getMember(any()))
               .thenReturn(new GitHubMember("nickname", 1L));
        
        //when
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                                                                  .pathParam("memberType", memberType)
                                                                  .queryParam("code", "code")
                                                                  .when()
                                                                  .post("/login/{memberType}")
                                                                  .then()
                                                                  .log().all()
                                                                  .extract();
        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.cookies().get("JSESSIONID")).isEqualTo(response.sessionId());
            softAssertions.assertThat(memberRepository.findAll()).hasSize(1);
        });
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"GITHUB"})
    void 이미_존재하는_멤버라면_로그인에_성공한다(String memberType) {
        //given
        final String nickname = "nickname";
        final Long memberIdentityId = 1L;
        final Member member = new Member(new MemberIdentity(String.valueOf(memberIdentityId), MemberType.fromName(memberType)), nickname);
        memberRepository.save(member);
        
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenReturn(new GitHubToken("accessToken", "scope", "tokenType"));
        Mockito.when(gitHubApiClient.getMember(any()))
               .thenReturn(new GitHubMember(nickname, memberIdentityId));
        
        //when
        RestAssured.given().log().all()
                   .pathParam("memberType", memberType)
                   .queryParam("code", "code")
                   .when()
                   .post("/login/{memberType}")
                   .then()
                   .log().all()
                   .extract();
        
        //then
        assertSoftly(softAssertions -> softAssertions.assertThat(memberRepository.findAll()).hasSize(1));
    }
    
    @Test
    void 존재하지_않는_멤버타입이면_로그인에_실패한다() {
        //when
        final String wrongMemberType = "SDFE";
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                                                            .pathParam("memberType", wrongMemberType)
                                                            .queryParam("code", "code")
                                                            .when()
                                                            .post("/login/{memberType}")
                                                            .then()
                                                            .log().all()
                                                            .extract();
        //then
        final int actualStatusCode = response.statusCode();
        final int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        final ExceptionResponse actualResponse = response.as(ExceptionResponse.class);
        final ExceptionResponse expectedResponse = new ExceptionResponse(INVALID_REQUEST.errorCode(),
                INVALID_REQUEST.errorMessage());
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
            softAssertions.assertThat(actualResponse).isEqualTo(expectedResponse);
        });
    }
    
    @Test
    void 인증코드가_유효하지않은_경우_로그인에_실패한다() {
        //given
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenThrow(HttpClientErrorException.class);
        
        //when
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                                                            .pathParam("memberType", "GITHUB")
                                                            .queryParam("code", "code")
                                                            .when()
                                                            .post("/login/{memberType}")
                                                            .then()
                                                            .log().all()
                                                            .extract();
        //then
        final int actualStatusCode = response.statusCode();
        final int expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        final ExceptionResponse actualResponse = response.as(ExceptionResponse.class);
        final ExceptionResponse expectedResponse = new ExceptionResponse(INVALID_OAUTH_API_REQUEST.errorCode(),
                INVALID_OAUTH_API_REQUEST.errorMessage());
    
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
            softAssertions.assertThat(actualResponse).isEqualTo(expectedResponse);
        });
    }
}
