package springboot.springsecurity_OAuth2.common.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
