package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/*swagger file*/
/*http://localhost:8080/v2/api-docs*/

@EnableSwagger2
@Configuration
public class SwaggerConfiguration
{

        @Bean
        public Docket securityAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("securityService")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/api/security/**"))
                    .build().produces(Collections.singleton("application/json"))
                    .apiInfo(metaInfo());
        }

        @Bean
        public Docket bicycleAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bicycleService")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/bicycle/**"))
                .build().produces(Collections.singleton("application/json"))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .apiInfo(metaInfo());
         }

        @Bean
        public Docket brandAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("brandService")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/brand/**"))
                .build().produces(Collections.singleton("application/json"))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .apiInfo(metaInfo());
        }

        @Bean
        public Docket purchaseAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("purchaseService")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/purchase/**"))
                .build().produces(Collections.singleton("application/json"))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .apiInfo(metaInfo());
        }

        @Bean
        public Docket shifterAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("shifterService")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/shifter/**"))
                .build().produces(Collections.singleton("application/json"))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .apiInfo(metaInfo());
        }
        @Bean
        public Docket sizeAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("sizeService")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/api/size/**"))
                    .build().produces(Collections.singleton("application/json"))
                    .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                    .apiInfo(metaInfo());
        }
        @Bean
        public Docket typeAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("typeService")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/api/type/**"))
                    .build().produces(Collections.singleton("application/json"))
                    .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                    .apiInfo(metaInfo());
        }

        @Bean
        public Docket wheelDiameterAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("wheelDiameterService")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/api/wheelDiameter/**"))
                    .build().produces(Collections.singleton("application/json"))
                    .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                    .apiInfo(metaInfo());
        }

        @Bean
        public Docket userAPI()
        {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("userService")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/user/**"))
                .build().produces(Collections.singleton("application/json"))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .apiInfo(metaInfo());
        }


    private ApiInfo metaInfo()
    {
        return new ApiInfoBuilder().title("Swagger for Demo app")
                                   .description("Demo application using swagger")
                                   .contact
                                   (
                                       new Contact
                                       (
                                           "Vastag Atila",
                                           "",
                                           "wasyster@gmail.com"
                                       )
                                   )
                                   .license("MIT")
                                   .version("1.0.0")
                                   .build();
    }
}
