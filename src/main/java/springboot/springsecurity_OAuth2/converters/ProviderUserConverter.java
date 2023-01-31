package springboot.springsecurity_OAuth2.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
