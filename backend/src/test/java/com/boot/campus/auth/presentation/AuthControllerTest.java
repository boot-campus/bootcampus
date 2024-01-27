package com.boot.campus.auth.presentation;

import com.boot.campus.auth.infrastructure.github.GitHubApiClient;
import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.common.RedisTestContainer;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberIdentity;
import com.boot.campus.member.domain.MemberType;
import com.boot.campus.member.persistence.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
@ActiveProfiles("test")
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
        memberRepository.save(new Member(new MemberIdentity(String.valueOf(memberIdentityId), MemberType.fromName(memberType)), nickname));
        
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenReturn(new GitHubToken("accessToken", "scope", "tokenType"));
        Mockito.when(gitHubApiClient.getMember(any()))
               .thenReturn(new GitHubMember(nickname, memberIdentityId));
        
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
            softAssertions.assertThat(memberRepository.findAll()).hasSize(1);
        });
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"SDFEF"})
    void 존재하지_않는_멤버타입이면_로그인에_실패한다(String wrongMemberType) {
        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                                                            .pathParam("memberType", wrongMemberType)
                                                            .queryParam("code", "code")
                                                            .when()
                                                            .post("/login/{memberType}")
                                                            .then()
                                                            .log().all()
                                                            .extract();
        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        });
    }
    
}
