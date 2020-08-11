package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "1";

    @Test
    public void test_prepareGreeting_nullName_returnsFallbackName() throws Exception {
        // given
        var mockRepository = alwaysReturnigHelloRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,"-1");

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() throws Exception {
        // given
        var mockRepository = alwaysReturnigHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";

        // when
        var result = SUT.prepareGreeting(name,"-1");

        // then
        assertEquals(WELCOME+" " + name + "!", result);
    }


    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception {
        // given
        var mockRepository = nonExistingRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,"-1");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg()+" " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() throws Exception {
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,null);

        // then
        assertEquals(FALLBACK_ID_WELCOME+" " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() throws Exception {
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,"abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME+" " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository nonExistingRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null,FALLBACK_ID_WELCOME,null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturnigHelloRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME,"-1"));
            }
        };
    }

}