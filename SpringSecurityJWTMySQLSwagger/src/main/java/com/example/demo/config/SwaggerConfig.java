package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
   info = @Info(
        title = "Know Program", 
        description = "This API is for User Info",
        summary = "API Contains Summary Info",
        termsOfService = "https://www.knowprogram.com/terms-and-conditions/",
        contact = @Contact(
                name="Rocco Jerry",
                email="info@knowprogram.com",
                url="https://www.knowprogram.com"
        ),
        license = @License(name="KnowProgram License"),
        version = "API/V1"
   ),
   security = @SecurityRequirement(name="KnowProgramSecurity")
)
@SecurityScheme(
        name="KnowProgramSecurity", 
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Berear ",
        description = "This is My Basic Security"
)
public class SwaggerConfig {
}
