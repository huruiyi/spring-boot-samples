#转载自：https://github.com/lexburner/oauth2-demo.git

.s.o.p.e.FrameworkEndpointHandlerMapping : Looking for request mappings in application context:
org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@43195e57: startup date [Sun Mar 24 23:53:06 CST 2019];
root of context hierarchy
.s.o.p.e.FrameworkEndpointHandlerMapping : 2 request handler methods found on class
org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint: {public org.springframework.web.servlet.View
org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.approveOrDeny(
java.util.Map,java.util.Map,org.springframework.web.bind.support.SessionStatus,java.security.Principal)={[/oauth/authorize],methods=[POST]
,params=[user_oauth_approval]}, public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.authorize(
java.util.Map,java.util.Map,org.springframework.web.bind.support.SessionStatus,java.security.Principal)={[/oauth/authorize]}}
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/authorize],methods=[POST],params=[user_oauth_approval]}" onto public
org.springframework.web.servlet.View org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.approveOrDeny(java.util.Map<
java.lang.String, java.lang.String>,java.util.Map<java.lang.String, ?>,org.springframework.web.bind.support.SessionStatus,java.security.Principal)
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/authorize]}" onto public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.authorize(java.util.Map<java.lang.String, java.lang.Object>,java.util.Map<
java.lang.String, java.lang.String>,org.springframework.web.bind.support.SessionStatus,java.security.Principal)
.s.o.p.e.FrameworkEndpointHandlerMapping : 2 request handler methods found on class
org.springframework.security.oauth2.provider.endpoint.TokenEndpoint: {public org.springframework.http.ResponseEntity
org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.getAccessToken(java.security.Principal,java.util.Map) throws
org.springframework.web.HttpRequestMethodNotSupportedException={[/oauth/token],methods=[GET]}, public org.springframework.http.ResponseEntity
org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(java.security.Principal,java.util.Map) throws
org.springframework.web.HttpRequestMethodNotSupportedException={[/oauth/token],methods=[POST]}}
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/token],methods=[GET]}" onto public org.springframework.http.ResponseEntity<
org.springframework.security.oauth2.common.OAuth2AccessToken> org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.getAccessToken(
java.security.Principal,java.util.Map<java.lang.String, java.lang.String>) throws org.springframework.web.HttpRequestMethodNotSupportedException
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/token],methods=[POST]}" onto public org.springframework.http.ResponseEntity<
org.springframework.security.oauth2.common.OAuth2AccessToken> org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(
java.security.Principal,java.util.Map<java.lang.String, java.lang.String>) throws org.springframework.web.HttpRequestMethodNotSupportedException
.s.o.p.e.FrameworkEndpointHandlerMapping : 1 request handler methods found on class
org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint: {public java.util.Map
org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint.checkToken(java.lang.String)={[/oauth/check_token]}}
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/check_token]}" onto public java.util.Map<java.lang.String, ?>
org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint.checkToken(java.lang.String)
.s.o.p.e.FrameworkEndpointHandlerMapping : 1 request handler methods found on class
org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint: {public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint.getAccessConfirmation(
java.util.Map,javax.servlet.http.HttpServletRequest) throws java.lang.Exception={[/oauth/confirm_access]}}
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/confirm_access]}" onto public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint.getAccessConfirmation(java.util.Map<java.lang.String,
java.lang.Object>,javax.servlet.http.HttpServletRequest) throws java.lang.Exception
.s.o.p.e.FrameworkEndpointHandlerMapping : 1 request handler methods found on class
org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint: {public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint.handleError(javax.servlet.http.HttpServletRequest)={[/oauth/error]}}
.s.o.p.e.FrameworkEndpointHandlerMapping : Mapped "{[/oauth/error]}" onto public org.springframework.web.servlet.ModelAndView
org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint.handleError(javax.servlet.http.HttpServletRequest)
s.c.a.w.c.WebSecurityConfigurerAdapter$3 : No authenticationProviders and no parentAuthenticationManager defined. Returning null.
eGlobalAuthenticationAutowiredConfigurer : Eagerly initializing
{securityConfiguration=moe.cnkirito.security.oauth2.config.SecurityConfiguration$$EnhancerBySpringCGLIB$$82eace6a@174e1b69}
edFilterInvocationSecurityMetadataSource : Adding web access control expression 'fullyAuthenticated', for Ant [pattern='/oauth/token']
edFilterInvocationSecurityMetadataSource : Adding web access control expression 'denyAll()', for Ant [pattern='/oauth/token_key']
edFilterInvocationSecurityMetadataSource : Adding web access control expression 'denyAll()', for Ant [pattern='/oauth/check_token']
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: OrRequestMatcher [requestMatchers=[Ant [pattern='/oauth/token'],
Ant [pattern='/oauth/token_key'],
Ant [pattern='/oauth/check_token']]], [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@3d278b4d, org.springframework.security.web.context.SecurityContextPersistenceFilter@7c129ef6, org.springframework.security.web.header.HeaderWriterFilter@38b5f25, org.springframework.security.web.authentication.logout.LogoutFilter@3d7b1f1c, org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter@6c575325, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@6a48a7f3, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@5a8cbffe, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@554cd74a, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@4096aa05, org.springframework.security.web.session.SessionManagementFilter@548d5ed3, org.springframework.security.web.access.ExceptionTranslationFilter@2776015d, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@3fbcfe81]
edFilterInvocationSecurityMetadataSource : Adding web access control expression 'authenticated', for Ant [pattern='/order/**']
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain:
org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration$NotOAuthRequestMatcher@2f508f3c, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@3ed03652, org.springframework.security.web.context.SecurityContextPersistenceFilter@205bed61, org.springframework.security.web.header.HeaderWriterFilter@54bca971, org.springframework.security.web.authentication.logout.LogoutFilter@3e48d38, org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter@5c73f672, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@129fed45, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@23592946, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@4aedaf61, org.springframework.security.web.session.SessionManagementFilter@23706db8, org.springframework.security.web.access.ExceptionTranslationFilter@16a49a5d, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@7a34f66a]
edFilterInvocationSecurityMetadataSource : Adding web access control expression 'permitAll', for Ant [pattern='/oauth/**']
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.w.a.i.FilterSecurityInterceptor    : Validated configuration attributes
o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: OrRequestMatcher [
requestMatchers=[org.springframework.security.web.util.matcher.AnyRequestMatcher@1]], [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@151ef57f, org.springframework.security.web.context.SecurityContextPersistenceFilter@1d0a61c8, org.springframework.security.web.header.HeaderWriterFilter@1e141e42, org.springframework.security.web.csrf.CsrfFilter@33d53216, org.springframework.security.web.authentication.logout.LogoutFilter@3db663d0, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@46731692, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@782bf610, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@10895b16, org.springframework.security.web.session.SessionManagementFilter@228cea97, org.springframework.security.web.access.ExceptionTranslationFilter@62d73ead, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@47fbc56]
o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of
type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice:
org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@43195e57: startup date [Sun Mar 24 23:53:06 CST 2019];
root of context hierarchy
s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/order/{id}],methods=[GET]}" onto public java.lang.String
moe.cnkirito.security.oauth2.web.TestEndpoints.getOrder(java.lang.String)
s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/product/{id}],methods=[GET]}" onto public java.lang.String
moe.cnkirito.security.oauth2.web.TestEndpoints.getProduct(java.lang.String)
s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String,
java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView
org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(
javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of
type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of
type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator/health],methods=[GET]
,produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto public java.lang.Object
org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping$OperationHandler.handle(
javax.servlet.http.HttpServletRequest,java.util.Map<java.lang.String, java.lang.String>)
s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator/info],methods=[GET]
,produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto public java.lang.Object
org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping$OperationHandler.handle(
javax.servlet.http.HttpServletRequest,java.util.Map<java.lang.String, java.lang.String>)
s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator],methods=[GET]
,produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto protected java.util.Map<java.lang.String, java.util.Map<
java.lang.String, org.springframework.boot.actuate.endpoint.web.Link>>
org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping.links(
javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)


--------------------------------------------------------------------------------------------------------------------------------------------

http://localhost:8080/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456
等同于：http://client_2:123456@localhost:8090/oauth/token?username=user_1&password=123456&grant_type=password&scope=select

```json
{
    "access_token": "3df8a157-a3eb-42f7-aa08-a79dccdc3fc8",
    "token_type": "bearer",
    "refresh_token": "8d10466a-47ef-4bdd-9169-a4676f23751d",
    "expires_in": 43107,
    "scope": "select"
}
```

http://localhost:8080/api/profile?access_token=DxqOpr2AGUnwqaEPAS1r5PyJlPA

http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=8d10466a-47ef-4bdd-9169-a4676f23751d&client_id=client_2&client_secret=123456

--------------------------------------------------------------------------------------------------------------------------------------------

http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456

```json
{
    "access_token": "ad75889d-0f13-4a97-abb9-86d24011ed94",
    "token_type": "bearer",
    "expires_in": 43145,
    "scope": "select"
}
```

http://localhost:8080/order/1?access_token=ad75889d-0f13-4a97-abb9-86d24011ed94

--------------------------------------------------------------------------------------------------------------------------------------------
spring-oauth2-tests-jpa .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")

"authorization_code"
"password",
"client_credentials",
"implicit",
"refresh_token"